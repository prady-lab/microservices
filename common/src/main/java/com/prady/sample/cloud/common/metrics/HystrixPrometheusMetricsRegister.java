/**
 *
 */

package com.prady.sample.cloud.common.metrics;

import com.soundcloud.prometheus.hystrix.HystrixPrometheusMetricsPublisher;

/**
 * @author Pradeep Balakrishnan
 */
public class HystrixPrometheusMetricsRegister {

    public static void register(String applicationName) {
        HystrixPrometheusMetricsPublisher.register(applicationName);
    }
}
