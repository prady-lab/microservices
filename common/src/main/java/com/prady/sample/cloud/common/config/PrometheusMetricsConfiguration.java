/**
 *
 */

package com.prady.sample.cloud.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.prady.sample.cloud.common.interceptor.ServiceMetricsInterceptor;
import com.prady.sample.cloud.common.metrics.HystrixPrometheusMetricsRegister;

/**
 * @author Pradeep Balakrishnan
 */
@Configuration
@ConditionalOnExpression("${app.prometheus.metric.enabled:true}")
public class PrometheusMetricsConfiguration {

    @Configuration
    public static class WebMvcConfig extends WebMvcConfigurerAdapter {

        @Autowired
        private ServiceMetricsInterceptor serviceMetricsInterceptor;

        @Value("${spring.application.name}")
        private String applicationName;

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            HystrixPrometheusMetricsRegister.register(applicationName);
            registry.addInterceptor(serviceMetricsInterceptor);
        }
    }
}
