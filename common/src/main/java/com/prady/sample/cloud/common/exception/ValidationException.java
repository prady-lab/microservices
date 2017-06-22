/**
 *
 */

package com.prady.sample.cloud.common.exception;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import net.sf.oval.ConstraintViolation;

/**
 * @author Prady
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ValidationException extends RuntimeException {

    private List<ConstraintViolation> constraintViolations;

    /**
     *
     */
    private static final long serialVersionUID = -5355053606481348640L;

    public ValidationException(ConstraintViolation constraintViolation) {
        this(Arrays.asList(new ConstraintViolation[] { constraintViolation }));
    }

    public ValidationException(List<ConstraintViolation> constraintViolations) {
        super("Validation Errors");
        this.constraintViolations = constraintViolations;
    }

    /**
     * @return the constraintViolations
     */
    public List<ConstraintViolation> getConstraintViolations() {
        return constraintViolations;
    }
}
