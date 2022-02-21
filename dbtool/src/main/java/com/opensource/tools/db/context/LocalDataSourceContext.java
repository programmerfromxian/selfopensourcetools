package com.opensource.tools.db.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

/**
 * 描述信息
 *
 * @author yangzai
 * @date 2022-02-22
 */
public class LocalDataSourceContext {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalDataSourceContext.class);

    private static ThreadLocal<DataSource> dataSourceThreadLocal = new ThreadLocal<>();

    public static void setLocalDataSource(DataSource dataSource) {
        dataSourceThreadLocal.set(dataSource);
    }

    public static DataSource getLocalDataSource() {
        return dataSourceThreadLocal.get();
    }

    public static void remove() {
        dataSourceThreadLocal.remove();
    }

}
