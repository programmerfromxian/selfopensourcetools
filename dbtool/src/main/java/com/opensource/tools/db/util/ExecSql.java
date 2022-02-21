package com.opensource.tools.db.util;

import com.opensource.tools.common.exception.OpenSourceException;
import com.opensource.tools.db.context.LocalDataSourceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 描述信息
 *
 * @author yangzai
 * @date 2022-02-17
 */
public class ExecSql {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExecSql.class);

    public static boolean connectTest(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        try {
            jdbcTemplate.queryForObject("select 1", Object.class);
        } catch (CannotGetJdbcConnectionException e) {
            handleException(e);
        }
        LocalDataSourceContext.setLocalDataSource(dataSource);
        return true;
    }

    public static List<String> queryForList(DataSource dataSource, String sql) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getObject(1).toString());
    }

    public static List<Map<String, Object>> queryForMapList(DataSource dataSource, String sql) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.queryForList(sql);
    }

    public static Map<String, Object> queryForMap(DataSource dataSource, String sql) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.queryForMap(sql);
    }

    private static void handleException(Throwable ex) {
        LOGGER.error("JDBC exception: ", ex);
        throw new OpenSourceException(ex);
    }

}
