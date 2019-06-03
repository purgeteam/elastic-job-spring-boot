package com.elasticjob.starter;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.elasticjob.starter.factory.SpringJobSchedulerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ZookeeperRegistryCenter Metadata Endpoints Auto-{@link Configuration}
 *
 * @author purgeyao
 * @since 1.0
 */
@Configuration
public class JobRegistryCenterAutoConfiguration {

    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter regCenter(@Value("${spring.elasticjob.regCenter.serverList}") final String serverList,
                                             @Value("${spring.elasticjob.regCenter.namespace}") final String namespace) {
        return new ZookeeperRegistryCenter(new ZookeeperConfiguration(serverList, namespace));
    }

}
