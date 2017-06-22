/**
 *
 */

package com.prady.sample.cloud.common.validation;

import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

/**
 * @author Prady
 */
public class ValidationUtil {

    public static List<ConstraintViolation> validate(Object o) {
        Validator validator = new Validator();
        return validator.validate(o);
    }

}
