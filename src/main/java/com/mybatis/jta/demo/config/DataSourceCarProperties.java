package com.mybatis.jta.demo.config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description: jta-demo
 * Create by liangxifeng on 19-9-27
 */
@Component //自动注入
@ConfigurationProperties(prefix = "spring.datasource.car")
@Data
public class DataSourceCarProperties {
    private String type;
    private String driverClassName;
    private String url;
    private String username;
    private String password;

    private int initialSize;
    private int minIdle;
    private int maxActive;
    private long maxWait;
    private long timeBetweenEvictionRunsMillis;
    private long minEvictableIdleTimeMillis;
    private String validationQuery;
    private boolean testWhileIdle;
    private boolean testOnBorrow;
    private boolean testOnReturn;
    private boolean poolPreparedStatements;
    private int maxPoolPreparedStatementPerConnectionSize;
    private List filters;
    private String connectionProperties;
}