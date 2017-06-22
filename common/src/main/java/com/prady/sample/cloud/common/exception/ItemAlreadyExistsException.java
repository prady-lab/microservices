/**
 *
 */

package com.prady.sample.cloud.common.exception;

import java.text.MessageFormat;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Prady
 */
@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class ItemAlreadyExistsException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -5355053606481348640L;

    public ItemAlreadyExistsException(String itemId) {
        this(itemId, "Item");
    }

    public ItemAlreadyExistsException(String itemId, String itemType) {
        super(MessageFormat.format("{0} ''{1}'' already exists", itemId, itemType));
    }
}
