package com.purgeteam.elasticjob.starter.condition;

import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.purgeteam.elasticjob.starter.JobDataSourceProperties;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * {@link JobEventConfiguration} 策略加载
 *
 * @author <a href="mailto:yaoonlyi@gmail.com">purgeyao</a>
 * @since 1.0.0
 */
public class DataSourceCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String enable = context.getEnvironment().getProperty(JobDataSourceProperties.PREFIX + ".enable");
        if (enable == null) {
            enable = "true";
        }
        return "true".equals(enable);
    }
}
