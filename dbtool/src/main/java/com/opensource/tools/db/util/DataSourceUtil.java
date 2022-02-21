package com.opensource.tools.db.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.opensource.tools.db.constant.DatabaseEnum;
import com.opensource.tools.db.context.LocalDataSourceContext;
import com.opensource.tools.db.model.ConnectInfo;

import javax.sql.DataSource;

/**
 * 描述信息
 *
 * @author yangzai
 * @date 2022-02-17
 */
public class DataSourceUtil {

    /**
     * 创建数据路连接池
     *
     * @param connectInfo connect info
     * @return datasource
     */
    public static DataSource createLocalDataSource(ConnectInfo connectInfo) {
        DatabaseEnum databaseEnum = DatabaseEnum.typeOf(connectInfo.getType());
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUsername(connectInfo.getUsername());
        druidDataSource.setPassword(connectInfo.getPassword());
        druidDataSource.setDriverClassName(databaseEnum.getDriver());
        druidDataSource.setUrl(connectInfo.getUrl());
        druidDataSource.setMaxWait(3000);
        druidDataSource.setBreakAfterAcquireFailure(true);
        druidDataSource.setConnectionErrorRetryAttempts(2);
        LocalDataSourceContext.setLocalDataSource(druidDataSource);
        return druidDataSource;
    }

}
