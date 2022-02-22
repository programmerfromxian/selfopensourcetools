package com.opensource.tools.db.service.impl;

import com.opensource.tools.db.constant.DatabaseEnum;
import com.opensource.tools.db.model.*;
import com.opensource.tools.db.service.DatabaseService;
import com.opensource.tools.db.util.DataSourceUtil;
import com.opensource.tools.db.util.ExecSql;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 描述信息
 *
 * @author yangzai
 * @date 2022-02-16
 */
@Service
public class DatabaseServiceImpl implements DatabaseService {

    @Override
    public boolean testConnection(ConnectInfo connectInfo) {
        connectInfo.setUrl(getUrl(connectInfo));
        DataSource dataSource = DataSourceUtil.createLocalDataSource(connectInfo);
        return ExecSql.connectTest(dataSource);
    }

    @Override
    public ConnectResult connect(ConnectInfo connectInfo) {
        connectInfo.setUrl(getUrl(connectInfo));
        ConnectResult connectResult = new ConnectResult();
        DataSource dataSource = DataSourceUtil.createLocalDataSource(connectInfo);
        DatabaseEnum databaseEnum = DatabaseEnum.typeOf(connectInfo.getType());
        // 查询所有的数据库
        List<String> databaseList = ExecSql.queryForList(dataSource, getDataBasesSql(connectInfo.getType()));
        List<DataBase> result = new ArrayList<>();
        for (String databaseName : databaseList) {
            // 查询数据库的表
            DataBase dataBase = new DataBase();
            dataBase.setName(databaseName);
            dataBase.setTableList(queryDataBaseTables(dataSource, databaseName, databaseEnum));
            result.add(dataBase);
        }
        connectResult.setDataBaseList(result);
        return connectResult;
    }

    @Override
    public RecordResult queryRecord(String tableName, int pageNumber, int pageSize, ConnectInfo connectInfo) {
        connectInfo.setUrl(getUrl(connectInfo));
        DataSource dataSource = DataSourceUtil.createLocalDataSource(connectInfo);
        DatabaseEnum databaseEnum = DatabaseEnum.typeOf(connectInfo.getType());
        RecordResult recordResult = new RecordResult();
        Map<String, Object> countMap = ExecSql.queryForMap(dataSource, getCountSql(databaseEnum, tableName));
        int count = Integer.parseInt(countMap.values().stream().findFirst().get().toString());
        if (count == 0) {
            return recordResult;
        }
        List<Map<String, Object>> mapList = ExecSql.queryForMapList(dataSource, getRecordSql(databaseEnum, tableName, pageNumber, pageSize));
        recordResult.setTotalCount(count);
        // 没有查询到数据
        if (CollectionUtils.isEmpty(mapList)) {
            return recordResult;
        }
        List<String> columnList = new ArrayList<>();
        Map<String, Object> first = mapList.get(0);
        for (Map.Entry<String, Object> entry : first.entrySet()) {
            columnList.add(entry.getKey());
        }
        recordResult.setColumnList(columnList);
        recordResult.setRecordList(new ArrayList<>());
        for (Map<String, Object> map : mapList) {
            List<Object> record = new ArrayList<>();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                record.add(entry.getValue());
            }
            recordResult.getRecordList().add(record);
        }
        return recordResult;
    }

    private List<Table> queryDataBaseTables(DataSource dataSource, String databaseName, DatabaseEnum databaseEnum) {
        List<Table> result = new ArrayList<>();
        List<String> tableList = ExecSql.queryForList(dataSource, getTablesSql(databaseEnum.getType(), databaseName));
        for (String tableName : tableList) {
            // 查询表中所有的字段
            Table table = new Table();
            table.setName(tableName);
            table.setColumnList(queryTableColumns(dataSource, tableName, databaseEnum));
            result.add(table);
        }
        return result;
    }

    private List<Column> queryTableColumns(DataSource dataSource, String tableName, DatabaseEnum databaseEnum) {
        String columnSql = getColumnsSql(tableName, databaseEnum);
        List<Map<String, Object>> columnsMapList = ExecSql.queryForMapList(dataSource, columnSql);
        List<Column> columnList = new ArrayList<>();
        for (Map<String, Object> map : columnsMapList) {
            String name = map.get("COLUMN_NAME").toString();
            String type = map.get("COLUMN_TYPE").toString();
            Column column = new Column(name, type);
            columnList.add(column);
        }
        return columnList;
    }

    private String getColumnsSql(String tableName, DatabaseEnum databaseEnum) {
        String type = databaseEnum.getType();
        if (type.equalsIgnoreCase("mysql")) {
            return String.format(databaseEnum.getColumnsSql(), tableName);
        }
        return null;
    }

    private String getUrl(ConnectInfo connectInfo) {
        if (connectInfo.getType().equalsIgnoreCase("mysql")) {
            return String.format(DatabaseEnum.typeOf(connectInfo.getType()).getUrl(), connectInfo.getHost(), connectInfo.getPort());
        }
        return null;
    }

    private String getTablesSql(String type, String databaseName) {
        if (type.equalsIgnoreCase("mysql")) {
            return String.format(DatabaseEnum.typeOf(type).getTablesSql(), databaseName);
        }
        return null;
    }

    private String getDataBasesSql(String type) {
        if (type.equalsIgnoreCase("mysql")) {
            return DatabaseEnum.typeOf(type).getDatabasesSql();
        }
        return null;
    }

    private String getRecordSql(DatabaseEnum databaseEnum, String tableName, int pageNumber, int pageSize) {
        if (databaseEnum.getType().equalsIgnoreCase("mysql")) {
            int offset = (pageNumber - 1) * pageSize;
            return String.format(databaseEnum.getRecordSql(), tableName, offset, pageSize);
        }
        return null;
    }

    private String getCountSql(DatabaseEnum databaseEnum, String tableName) {
        if (databaseEnum.getType().equalsIgnoreCase("mysql")) {
            return String.format(databaseEnum.getCountSql(), tableName);
        }
        return null;
    }

}
