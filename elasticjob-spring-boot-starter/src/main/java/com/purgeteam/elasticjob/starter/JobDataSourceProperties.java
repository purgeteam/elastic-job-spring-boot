package com.purgeteam.elasticjob.starter;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author purgeyao
 * @since 1.0
 */
@Data
@ToString
@ConfigurationProperties(JobDataSourceProperties.PREFIX)
public class JobDataSourceProperties {

    public static final String PREFIX = "spring.elasticjob.datasource";

    /**
     * 是否启用数据库 默认启用
     */
    private Boolean enable = true;

    private String url;

    private String driverClassName;

    private String username;

    private String password;
}
