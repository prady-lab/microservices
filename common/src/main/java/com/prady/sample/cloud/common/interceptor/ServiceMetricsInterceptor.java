/**
 *
 */

package com.prady.sample.cloud.common.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.prady.sample.cloud.common.metrics.MetricsCollector;

/**
 * @author Pradeep Balakrishnan
 */
@Component
public class ServiceMetricsInterceptor extends HandlerInterceptorAdapter {
    private static final String REQ_PARAM_TIMING = "timing";

    @Autowired
    private MetricsCollector metricsCollector;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute(REQ_PARAM_TIMING, System.currentTimeMillis());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Long timingAttr = (Long) request.getAttribute(REQ_PARAM_TIMING);
        Long completedTime = System.currentTimeMillis() - timingAttr;
        String handlerLabel = handler.toString();
        if (handler instanceof HandlerMethod) {
            Method method = ((HandlerMethod) handler).getMethod();
            handlerLabel = method.getDeclaringClass().getSimpleName() + "." + method.getName();
        }

        Integer statusCode = response.getStatus();

        metricsCollector.httpResponseTimeInMills(completedTime, request.getMethod(), handlerLabel, Integer.toString(statusCode));
        metricsCollector.incrementHttpRequest(request.getMethod(), handlerLabel, Integer.toString(statusCode));
        if (null != ex || isErrorStatusCode(statusCode)) {
            metricsCollector.incrementFailedHttpRequest(request.getMethod(), handlerLabel, Integer.toString(statusCode));
        }

    }

    /**
     * @param statusCode
     * @return
     */
    private Boolean isErrorStatusCode(Integer statusCode) {
        return (statusCode > 299) ? Boolean.TRUE : Boolean.FALSE;
    }
}
