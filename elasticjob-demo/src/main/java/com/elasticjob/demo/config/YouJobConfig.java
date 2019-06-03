package com.elasticjob.demo.config;

import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.elasticjob.demo.scheduled.YouSimpleJob;
import com.elasticjob.starter.util.ElasticJobUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author purgeyao
 * @since 1.0
 */
//@Configuration
public class YouJobConfig {

    private static final String JOB_NAME = "youSimpleJob";

    private static final String CRON = "0 0/1 * * * ?";

    private static final int SHARDING_TOTAL_COUNT = 1;

    private static final String SHARDING_ITEM_PARAMETERS = null;

    private static final String JOB_PARAMETERS = null;

    @Resource
    private ZookeeperRegistryCenter regCenter;

    @Resource
    private JobEventConfiguration jobEventConfiguration;

    @Bean(initMethod = "init")
    public JobScheduler youSimpleJobScheduler(final YouSimpleJob youSimpleJob,
                                              @Value("${youJob.cron}") final String cron,
                                              @Value("${youJob.shardingTotalCount}") final int shardingTotalCount,
                                              @Value("${youJob.shardingItemParameters}") final String shardingItemParameters) {

        LiteJobConfiguration liteJobConfiguration = ElasticJobUtils
                .getLiteJobConfiguration(youSimpleJob.getClass(), JOB_NAME, cron,
                        shardingTotalCount, null, null);
        return new SpringJobScheduler(youSimpleJob, regCenter, liteJobConfiguration, jobEventConfiguration);
    }

}
