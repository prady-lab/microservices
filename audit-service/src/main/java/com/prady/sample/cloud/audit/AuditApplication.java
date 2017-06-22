
package com.prady.sample.cloud.audit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;

/**
 * @author Prady
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableConfigurationProperties
@EnablePrometheusEndpoint
@ComponentScan("com.prady.sample.cloud")
public class AuditApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(AuditApplication.class, args);
    }

}
