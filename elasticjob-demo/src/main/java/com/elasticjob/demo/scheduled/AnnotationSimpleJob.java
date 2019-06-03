package com.elasticjob.demo.scheduled;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.elasticjob.starter.annotation.ElasticJobScheduler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author purgeyao
 * @since 1.0
 */
@Slf4j
@ElasticJobScheduler(
        name = "AnnotationSimpleJob",
        cron = "0/8 * * * * ?",
        shardingItemParameters = "0=Beijing,1=Shanghai,2=Guangzhou",
        jobParameters = "123"
)
public class AnnotationSimpleJob implements SimpleJob {

//    @Transactional(rollbackForClassName = {"RuntimeException", "Exception"})
    @Override
    public void execute(ShardingContext shardingContext) {
        log.info(String.format("Thread ID: %s, 作业分片总数: %s, " +
                        "当前分片项: %s.当前参数: %s," +
                        "作业名称: %s.作业自定义参数: %s",
                Thread.currentThread().getId(),
                shardingContext.getShardingTotalCount(),
                shardingContext.getShardingItem(),
                shardingContext.getShardingParameter(),
                shardingContext.getJobName(),
                shardingContext.getJobParameter()
        ));
    }
}
