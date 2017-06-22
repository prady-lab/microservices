/**
 *
 */

package com.prady.sample.cloud.common.response;

import org.springframework.http.HttpStatus;

/**
 * @author Pradeep Balakrishnan
 */
public class ErrorResponse {

    private HttpStatus status;

    private String errorCode;
    private String errorMessage;

    /**
     * @param status
     * @param errorCode
     * @param errorMessage
     */
    public ErrorResponse(HttpStatus status, String errorCode, String errorMessage) {
        super();
        this.status = status;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    /**
     * @return the status
     */
    public HttpStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    /**
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
