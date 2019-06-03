package com.elasticjob.starter.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * job 启动初始化 {@link @interface}
 *
 * @author purgeyao
 * @since 1.0
 */
@Component
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ElasticJobScheduler {

    /**
     * 作业名称
     *
     * @return string
     */
    String name();

    /**
     * cron表达式
     *
     * @return string
     */
    String cron();

    /**
     * 作业分片总数
     *
     * @return int
     */
    int shardingTotalCount() default 1;

    /**
     * 分片序列号和参数用等号分隔
     * 如: 1=a,2=b,3=c
     *
     * @return String
     */
    String shardingItemParameters() default "";

    /**
     * 作业自定义参数
     *
     * @return String
     */
    String jobParameters() default "";

    /**
     * 是否开启数据记录
     *
     * @return boolean
     */
    boolean isEvent() default true;
}
