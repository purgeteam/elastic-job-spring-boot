package com.purgeteam.elasticjob.starter;

import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.purgeteam.elasticjob.starter.factory.SpringJobSchedulerFactory;
import com.purgeteam.elasticjob.starter.parser.JobConfParser;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Elasticjob Metadata Endpoints Auto-{@link Configuration}
 *
 * @author purgeyao
 * @since 1.0
 */
@Configuration
@EnableConfigurationProperties(ElasticJobProperties.class)
public class ElasticJobAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(JobEventConfiguration.class)
    public SpringJobSchedulerFactory springJobSchedulerFactory(
            ElasticJobProperties elasticJobProperties,
            ZookeeperRegistryCenter regCenter,
            JobEventConfiguration jobEventConfiguration) {
        return new SpringJobSchedulerFactory(elasticJobProperties, regCenter, jobEventConfiguration);
    }

    @Bean
    @ConditionalOnMissingBean(JobEventConfiguration.class)
    public SpringJobSchedulerFactory springJobSchedulerFactory(
            ElasticJobProperties elasticJobProperties,
            ZookeeperRegistryCenter regCenter) {
        return new SpringJobSchedulerFactory(elasticJobProperties, regCenter);
    }

    @Bean
    public JobConfParser jobConfParser(SpringJobSchedulerFactory springJobSchedulerFactory){
        return new JobConfParser(springJobSchedulerFactory);
    }
}
