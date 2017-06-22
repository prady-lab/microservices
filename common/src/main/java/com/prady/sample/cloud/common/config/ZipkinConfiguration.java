/**
 *
 */

package com.prady.sample.cloud.common.config;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.Span;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Prady
 */
@Configuration
public class ZipkinConfiguration {

    @Value("${spring.sleuth.sampler.ignoreSleuthExport}")
    private String ignoreZipkinExport;

    @Bean
    public Sampler defaultSampler() {
        return new Sampler() {
            @Override
            public boolean isSampled(Span span) {
                List<String> ignoreSpanNames = Arrays.asList(StringUtils.split(ignoreZipkinExport, ","));
                if (ignoreSpanNames.contains(span.getName())) {
                    return false;
                } else {
                    System.out.println(span + " " + span.getName() + " " + span.getBaggage());
                    return true;
                }
            }
        };
    }

}
