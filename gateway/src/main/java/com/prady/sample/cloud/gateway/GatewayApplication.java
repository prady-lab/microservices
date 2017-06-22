
package com.prady.sample.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;

import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;

/**
 * @author Prady
 */
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
@EnableConfigurationProperties
@EnablePrometheusEndpoint
@ComponentScan("com.prady.sample.cloud")
public class GatewayApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(GatewayApplication.class, args);
    }

}
