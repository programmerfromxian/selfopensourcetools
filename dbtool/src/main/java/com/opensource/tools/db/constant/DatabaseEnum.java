package com.opensource.tools.db.constant;

import java.util.Locale;

/**
 * database枚举
 *
 * @author yangzai
 * @date 2022-02-16
 */
public enum DatabaseEnum {

    MYSQL("mysql",
            "com.mysql.jdbc.Driver",
            "jdbc:mysql://%1$s:%2$s",
            "show databases",
            "select table_name from information_schema.tables where table_schema='%1$s'",
            "select * from information_schema.COLUMNS where TABLE_NAME = '%1$s'",
            "select * from %1$s limit %2$s,%3$s",
            "select count(*) from %1$s");

    private String type;

    private String driver;

    private String url;

    private String databasesSql;

    private String tablesSql;

    private String columnsSql;

    private String recordSql;

    private String countSql;

    DatabaseEnum(String type, String driver, String url, String databasesSql, String tablesSql, String columnsSql, String recordSql, String countSql) {
        this.type = type;
        this.driver = driver;
        this.url = url;
        this.databasesSql = databasesSql;
        this.tablesSql = tablesSql;
        this.columnsSql = columnsSql;
        this.recordSql = recordSql;
        this.countSql = countSql;
    }

    public static DatabaseEnum typeOf(String type) {
        return DatabaseEnum.valueOf(type.toUpperCase(Locale.ROOT));
    }

    public String getType() {
        return type;
    }

    public String getDriver() {
        return driver;
    }

    public String getUrl() {
        return url;
    }

    public String getDatabasesSql() {
        return databasesSql;
    }

    public String getTablesSql() {
        return tablesSql;
    }

    public String getColumnsSql() {
        return columnsSql;
    }

    public String getRecordSql() {
        return recordSql;
    }

    public String getCountSql() {
        return countSql;
    }
}
