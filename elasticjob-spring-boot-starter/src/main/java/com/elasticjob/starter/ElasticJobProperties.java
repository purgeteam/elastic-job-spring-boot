package com.elasticjob.starter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @author purgeyao
 * @since 1.0
 */
@Data
@ToString
@ConfigurationProperties(ElasticJobProperties.PREFIX)
public class ElasticJobProperties {

    public static final String PREFIX = "spring.elasticjob.scheduled";

    private Map<String, JobConfig> jobConfigMap = new HashMap<>();

    @Data
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JobConfig{

        private String jobName;

        private String cron;

        private int shardingTotalCount = 1;

        private String shardingItemParameters;

        private String jobParameters;

        private Boolean isEvent;
    }
}
