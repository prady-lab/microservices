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
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ItemNotFoundException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 7598017657764989747L;

    public ItemNotFoundException(String itemId) {
        this(itemId, "Item");
    }

    public ItemNotFoundException(String itemId, String itemType) {
        super(MessageFormat.format("{0} ''{1}'' not found", itemId, itemType));
    }
}
