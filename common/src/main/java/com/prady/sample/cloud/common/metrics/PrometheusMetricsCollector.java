/**
 *
 */

package com.prady.sample.cloud.common.metrics;

import org.springframework.stereotype.Component;

import io.prometheus.client.Counter;
import io.prometheus.client.Summary;

/**
 * @author Pradeep Balakrishnan
 */
@Component
public class PrometheusMetricsCollector implements MetricsCollector {

    private static final Counter requestTotal = Counter.build().name("http_requests_total").labelNames("method", "handler", "status")
            .help("Http Request Total").register();

    private static final Counter requestFailed = Counter.build().name("http_requests_failed").labelNames("method", "handler", "status")
            .help("Http Request Failed").register();

    private static final Summary responseTimeInMs = Summary.build().name("http_response_time_milliseconds")
            .labelNames("method", "handler", "status").help("Request completed time in milliseconds").register();

    /*
     * (non-Javadoc)
     * @see com.prady.sample.cloud.MetricsCollector#incrementHttpRequest(java.lang.String[])
     */
    @Override
    public void incrementHttpRequest(String... labelValues) {
        requestTotal.labels(labelValues).inc();
    }

    /*
     * (non-Javadoc)
     * @see com.prady.sample.cloud.MetricsCollector#incrementFailedHttpRequest(java.lang.String[])
     */
    @Override
    public void incrementFailedHttpRequest(String... labelValues) {
        requestFailed.labels(labelValues).inc();
    }

    /*
     * (non-Javadoc)
     * @see com.prady.sample.cloud.MetricsCollector#httpResponseTimeInMills(long)
     */
    @Override
    public void httpResponseTimeInMills(Long completedTimeInMillisec, String... labelValues) {
        responseTimeInMs.labels(labelValues).observe(completedTimeInMillisec);
    }

}
