package com.bumble.bee.app.exceptions;

import com.bumble.bee.app.models.dao.Entity;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Object id, Class<? extends Entity> entity) {
        super(String.format("Entity %s with id %s not found", entity.getSimpleName(), id));
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
