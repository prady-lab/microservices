/**
 *
 */

package com.prady.sample.cloud.common.metrics;

/**
 * @author Pradeep Balakrishnan
 */
public interface MetricsCollector {

    void incrementHttpRequest(String... labelValues);

    void incrementFailedHttpRequest(String... labelValues);

    void httpResponseTimeInMills(Long completedTimeInMillisec, String... labelValues);

}
