/**
 *
 */

package com.prady.sample.cloud.gateway.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prady.sample.cloud.common.response.ErrorResponse;

/**
 * @author Pradeep Balakrishnan
 */
@Controller
public class CustomErrorController implements ErrorController {

    private static final Logger log = LoggerFactory.getLogger(CustomErrorController.class);

    @Value("${error.path:/error}")
    private String errorPath;

    /*
     * (non-Javadoc)
     * @see org.springframework.boot.autoconfigure.web.ErrorController#getErrorPath()
     */
    @Override
    public String getErrorPath() {
        return errorPath;
    }

    @RequestMapping(value = "${error.path:/error}", produces = "application/vnd.error+json")
    public @ResponseBody ResponseEntity<ErrorResponse> error(HttpServletRequest request) {

        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        statusCode = statusCode != null ? statusCode : HttpStatus.INTERNAL_SERVER_ERROR.value();

        final Throwable ex = (Throwable) request.getAttribute("javax.servlet.error.exception");
        log.error("Error while invoking service ", ex);
        ErrorResponse errorRes = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "", ex != null ? ex.getLocalizedMessage() : "null");

        return ResponseEntity.status(statusCode).body(errorRes);
    }

}
