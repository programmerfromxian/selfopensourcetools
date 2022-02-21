package com.opensource.tools.db.filter;

import com.alibaba.druid.pool.DruidDataSource;
import com.opensource.tools.db.context.LocalDataSourceContext;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * 连接池释放
 *
 * @author yangzai
 * @date 2022-02-22
 */
public class DataSourceFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            DruidDataSource localDataSource = (DruidDataSource) LocalDataSourceContext.getLocalDataSource();
            if (localDataSource != null) {
                localDataSource.close();
                LocalDataSourceContext.remove();
            }
        }
    }

}
