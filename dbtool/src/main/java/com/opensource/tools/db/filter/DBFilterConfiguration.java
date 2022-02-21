package com.opensource.tools.db.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 描述信息
 *
 * @author yangzai
 * @date 2022-02-22
 */
@Configuration
public class DBFilterConfiguration {

    @Bean
    public FilterRegistrationBean<DataSourceFilter> dataSourceFilterFilterRegistrationBean() {
        FilterRegistrationBean<DataSourceFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new DataSourceFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

}
