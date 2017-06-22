/**
 *
 */

package com.prady.sample.cloud.common.audit;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Prady
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.METHOD })
public @interface Audit {

    public String module() default "DEFAULT_MODULE";

    public String action() default "DEFAULT_ACTION";

    public boolean isAuditMethodParam() default true;
}
