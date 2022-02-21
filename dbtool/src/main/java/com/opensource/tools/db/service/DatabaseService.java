package com.opensource.tools.db.service;

import com.opensource.tools.db.model.ConnectInfo;
import com.opensource.tools.db.model.ConnectResult;
import com.opensource.tools.db.model.RecordResult;

/**
 * 描述信息
 *
 * @author yangzai
 * @date 2022-02-16
 */
public interface DatabaseService {

    /**
     * 测试连接
     *
     * @param connectInfo connectInfo
     * @return result
     */
    boolean testConnection(ConnectInfo connectInfo);


    /**
     * 连接数据库
     *
     * @param connectInfo
     * @return 库、表、字段
     */
    ConnectResult connect(ConnectInfo connectInfo);

    /**
     * 查询记录
     *
     * @param tableName  tableName
     * @param pageNumber pageNumber
     * @param pageSize   pageSize
     * @param connectInfo connectInfo
     * @return record result
     */
    RecordResult queryRecord(String tableName, int pageNumber, int pageSize, ConnectInfo connectInfo);
}
