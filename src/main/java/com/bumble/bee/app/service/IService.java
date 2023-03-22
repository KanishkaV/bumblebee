package com.bumble.bee.app.service;

import com.bumble.bee.app.models.dao.Entity;

import java.util.*;

public interface IService<ENTITY extends Entity<KEY>, KEY> {

    boolean exists(KEY id);

    Optional<ENTITY> findById(KEY id);

    ENTITY create(ENTITY entity);

    ENTITY update(ENTITY entity);

    void delete(KEY id);

    Collection<ENTITY> getAll();
}
