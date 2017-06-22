/**
 *
 */

package com.prady.sample.cloud.common.advice;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.prady.sample.cloud.common.exception.ItemAlreadyExistsException;
import com.prady.sample.cloud.common.exception.ItemNotFoundException;
import com.prady.sample.cloud.common.exception.ValidationException;
import com.prady.sample.cloud.common.response.ErrorResponse;

import net.sf.oval.ConstraintViolation;

/**
 * @author Prady
 */
@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<Object> handleConflict(Exception ex, WebRequest request) {
        log.error("Error while invoking service " + ex.getMessage(), ex);
        HttpStatus errorStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse errorRes = null;
        if (ex instanceof ItemNotFoundException) {
            errorStatus = HttpStatus.NOT_FOUND;
        } else if (ex instanceof ItemAlreadyExistsException) {
            errorStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        } else if (ex instanceof ValidationException) {
            errorStatus = HttpStatus.BAD_REQUEST;
            errorRes = new ErrorResponse(errorStatus, "VALIDATION_ERRORS",
                    convertValidationToMessage(((ValidationException) ex).getConstraintViolations()));
        }
        if (null == errorRes) {
            errorRes = new ErrorResponse(errorStatus, "", ex.getLocalizedMessage());
        }
        return handleExceptionInternal(ex, errorRes, new HttpHeaders(), errorStatus, request);
    }

    /**
     * @param constraintViolations
     * @return
     */
    private String convertValidationToMessage(List<ConstraintViolation> constraintViolations) {
        if (!CollectionUtils.isEmpty(constraintViolations)) {
            return constraintViolations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(","));
        }
        return null;
    }
}
