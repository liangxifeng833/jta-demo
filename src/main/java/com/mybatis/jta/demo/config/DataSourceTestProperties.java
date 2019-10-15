package com.mybatis.jta.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * db_test数据源属性配置类
 * Create by liangxifeng on 19-9-27
 */
@Component //自动注入
@ConfigurationProperties(prefix = "spring.datasource.test")
@Data
public class DataSourceTestProperties {
    private String type;
    private String driverClassName;
    private String url;
    private String username;
    private String password;

    private long initialSize;
    private int minIdle;
    private int maxActive;
    private int maxWait;
    private int timeBetweenEvictionRunsMillis;
    private int minEvictableIdleTimeMillis;
    private String validationQuery;
    private boolean testWhileIdle;
    private boolean testOnBorrow;
    private boolean testOnReturn;
    private boolean poolPreparedStatements;
    private int maxPoolPreparedStatementPerConnectionSize;
    private List filters;
    private String connectionProperties;
}
