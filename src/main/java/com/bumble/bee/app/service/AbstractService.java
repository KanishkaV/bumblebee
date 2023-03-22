package com.bumble.bee.app.service;

import com.bumble.bee.app.models.dao.Entity;
import com.bumble.bee.app.repository.IRepository;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IteratorUtils;

import java.util.*;

@Slf4j
@Getter(AccessLevel.PROTECTED)
public abstract class AbstractService<ENTITY extends Entity<KEY>, KEY> {

    private final IRepository<ENTITY, KEY> repository;

    public AbstractService(IRepository<ENTITY, KEY> repository) {
        this.repository = repository;
    }

    public boolean exists(KEY id) {
        return this.getRepository().existsById(id);
    }

    public Optional<ENTITY> findById(KEY id) {
        return this.getRepository().findById(id);
    }

    public ENTITY create(ENTITY entity) {
        if (Objects.isNull(entity)) {
            throw new IllegalArgumentException(String.format("'%s' entity cannot be null", this.getEntityClass().getSimpleName()));
        }
        return this.getRepository().save(entity);
    }

    public ENTITY update(ENTITY entity) {
        if (Objects.isNull(entity)) {
            throw new IllegalArgumentException(String.format("'%s' entity cannot be null", this.getEntityClass().getSimpleName()));
        }
        log.info("Updating entity with id: {}", entity.getId());
        return this.getRepository().save(entity);
    }

    public void delete(KEY id) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("Id cannot be null or empty");
        }
        this.getRepository().deleteById(id);
    }

    public Collection<ENTITY> getAll() {
        return IteratorUtils.toList(this.getRepository().findAll().iterator());
    }

    abstract protected Class<ENTITY> getEntityClass();

}
