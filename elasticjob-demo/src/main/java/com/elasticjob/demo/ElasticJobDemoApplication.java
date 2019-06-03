package com.elasticjob.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author purgeyao
 * @since 1.0
 */
@SpringBootApplication
public class ElasticJobDemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ElasticJobDemoApplication.class, args);
    }

}
