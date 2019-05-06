package com.shuyu.service.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.shuyu.service.constant.DruidConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @package:com.ganinfo.common.configuration
 * @className:DruidConfig
 * @description:api 阿里druid  http://127.0.0.1:8080/druid/index.html
 * @author:Shuyu.Wang
 * @date:2018-11-30 18:13
 * @version:V1.0
**/
@Configuration
@Slf4j
public class DruidConfig {

    /**
     * @author:shuyu.wang
     * @description:注册一个StatViewServlet
     * @date: 2018/12/1 21:39
     * @param: null
     * @return: ServletRegistrationBean
     */
    @Bean
    public ServletRegistrationBean DruidStatViewServle2() {
        // org.springframework.boot.context.embedded.ServletRegistrationBean提供类的进行注册.
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), DruidConstant.URL_MAPPING_STRING);
        // 添加初始化参数：initParams
        // 白名单：
        servletRegistrationBean.addInitParameter(DruidConstant.ALLOW_KEY_STRING, DruidConstant.ALLOW_VALUE_STRING);
        // IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to
        // view this page.
//        servletRegistrationBean.addInitParameter("deny", "192.168.1.73");
        // 登录查看信息的账号密码.
        servletRegistrationBean.addInitParameter(DruidConstant.LOGIN_USERNAME_KEY_STRING, DruidConstant.LOGIN_USERNAME_VALUE_STRING);
        servletRegistrationBean.addInitParameter(DruidConstant.LOGIN_PASSWORD_KEY_STRING, DruidConstant.LOGIN_PASSWORD_VALUE_STRING);
        // 是否能够重置数据.
        servletRegistrationBean.addInitParameter(DruidConstant.RESET_ENABLE_KEY_STRING, DruidConstant.RESET_ENABLE_VALUE_STRING);
        return servletRegistrationBean;
    }

    /**
     * @author:shuyu.wang
     * @description:
     * @date: 2018/12/1 21:40
     * @return: org.springframework.boot.web.servlet.FilterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean druidStatFilter2() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        // 添加过滤规则.
        filterRegistrationBean.addUrlPatterns(DruidConstant.URL_PATTERNS_STRING);
        // 添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter(DruidConstant.EXCLUSIONS_KEY_STRING, DruidConstant.EXCLUSIONS_VALUE_STRING);
        return filterRegistrationBean;
    }


    /**
     * @author:shuyu.wang
     * @description:注册dataSouce，这里只是一个简单的例子，只注入了部分参数，其它自行注入
     * @date: 2018/12/1 21:40
     * @param: driver
     * @param: url
     * @param: username
     * @param: password
     * @param: maxActive
     * @return: javax.sql.DataSource
     */
    @Bean
    public DataSource druidDataSource(@Value("${spring.datasource.driverClassName}") String driver, @Value("${spring.datasource.url}") String url, @Value("${spring.datasource.username}") String username, @Value("${spring.datasource.password}") String password, @Value("${spring.datasource.maxActive}") int maxActive) {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driver);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setMaxActive(maxActive);
        try {
            druidDataSource.setFilters(DruidConstant.FILTERS_STRING);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return druidDataSource;
    }
}
