package com.elasticjob.starter.parser;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.elasticjob.starter.ElasticJobProperties;
import com.elasticjob.starter.annotation.ElasticJobScheduler;
import com.elasticjob.starter.factory.SpringJobSchedulerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;

import java.util.Map;

/**
 * {@link JobScheduler} registered
 *
 * @author purgeyao
 * @since 1.0
 */
@Slf4j
public class JobConfParser implements ApplicationContextAware {

    private SpringJobSchedulerFactory springJobSchedulerFactory;

    public JobConfParser(SpringJobSchedulerFactory springJobSchedulerFactory) {
        this.springJobSchedulerFactory = springJobSchedulerFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {

        Map<String, Object> beanMap = applicationContext.getBeansWithAnnotation(ElasticJobScheduler.class);
        beanMap.forEach((className, confBean) -> {

            // 获取注解信息组装配置
            Class<?> jobClass = confBean.getClass();
            if (!SimpleJob.class.isAssignableFrom(jobClass)) {
                throw new BeanCreationException(String.format("[JobConfParser] %s 初始化异常 请实现 %s", jobClass, SimpleJob.class));
            }
            ElasticJobScheduler config = AnnotationUtils.findAnnotation(jobClass, ElasticJobScheduler.class);
            ElasticJobProperties.JobConfig jobConfig = createJobConfig(config);

            // 构建SpringJobScheduler对象初始化
            SpringJobScheduler springJobScheduler = springJobSchedulerFactory.getSpringJobScheduler((SimpleJob) confBean, jobConfig);
            springJobScheduler.init();
            log.info("JobScheduler:{} registered successfully", jobConfig.getJobName());
        });

    }

    private static ElasticJobProperties.JobConfig createJobConfig(ElasticJobScheduler config) {

        return new ElasticJobProperties.JobConfig(
                config.name(), config.cron(),
                config.shardingTotalCount(), config.shardingItemParameters(),
                config.jobParameters(), config.isEvent()
        );
    }
}
