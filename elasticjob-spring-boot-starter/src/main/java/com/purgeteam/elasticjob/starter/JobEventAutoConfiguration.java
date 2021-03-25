package com.purgeteam.elasticjob.starter;

import com.alibaba.druid.pool.DruidDataSource;
import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.event.rdb.JobEventRdbConfiguration;
import com.purgeteam.elasticjob.starter.condition.DataSourceCondition;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * JobEventConfiguration Metadata Endpoints Auto-{@link Configuration}
 *
 * @author purgeyao
 * @since 1.0
 */
@Configuration
@EnableConfigurationProperties(JobDataSourceProperties.class)
public class JobEventAutoConfiguration {

    @Bean
    @Conditional(DataSourceCondition.class)
    public JobEventConfiguration jobEventConfiguration(JobDataSourceProperties jobDataSourceProperties) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(jobDataSourceProperties.getUrl());
        dataSource.setUsername(jobDataSourceProperties.getUsername());
        dataSource.setPassword(jobDataSourceProperties.getPassword());
        dataSource.setDriverClassName(jobDataSourceProperties.getDriverClassName());
        return new JobEventRdbConfiguration(dataSource);
    }

}
