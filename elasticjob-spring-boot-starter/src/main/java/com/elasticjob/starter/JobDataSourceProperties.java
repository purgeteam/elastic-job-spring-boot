package com.elasticjob.starter;

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

    private String url;

    private String driverClassName;

    private String username;

    private String password;
}
