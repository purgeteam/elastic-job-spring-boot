package com.elasticjob.starter.util;

import com.dangdang.ddframe.job.api.ElasticJob;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;

import java.util.Arrays;
import java.util.List;

/**
 * {@link ElasticJob} 工具类
 *
 * @author purgeyao
 * @since 1.0
 */
public class ElasticJobUtils {

    private static List<String> jobTypeNameList = Arrays.asList("SimpleJob", "DataflowJob", "ScriptJob");

    /**
     * 获取 {@link LiteJobConfiguration} 对象
     *
     * @param jobClass               定时器实现类
     * @param jobName                定时器名称
     * @param cron                   定时参数
     * @param shardingTotalCount     作业分片总数
     * @param shardingItemParameters 当前参数 可以为null
     * @param jobParameters          作业自定义参数 可以为null
     * @return {@link LiteJobConfiguration}
     */
    @SuppressWarnings("all")
    public static LiteJobConfiguration getLiteJobConfiguration(Class<? extends SimpleJob> jobClass,
                                                               final String jobName,
                                                               final String cron,
                                                               final int shardingTotalCount,
                                                               final String shardingItemParameters,
                                                               final String jobParameters) {

        // 解决CGLIB代理问题
        String jobTypeName = jobClass.getInterfaces()[0].getSimpleName();
        if (!jobTypeNameList.contains(jobTypeName)) {
            // jobTypeName = jobClass.getSuperclass().getInterfaces()[0].getSimpleName();
            jobClass = (Class<? extends SimpleJob>) jobClass.getSuperclass();
        }

        // 定义作业核心配置
        JobCoreConfiguration simpleCoreConfig = JobCoreConfiguration
                .newBuilder(jobName, cron, shardingTotalCount)
                .shardingItemParameters(shardingItemParameters)
                .jobParameter(jobParameters)
                .build();

        // 定义SIMPLE类型配置
        SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(simpleCoreConfig, jobClass.getCanonicalName());

        // 定义Lite作业根配置
        return LiteJobConfiguration.newBuilder(simpleJobConfig).overwrite(true).build();
    }
}
