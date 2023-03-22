package com.bumble.bee.app.rest;

import com.bumble.bee.app.convertors.IConverter;
import com.bumble.bee.app.exceptions.EntityNotFoundException;
import com.bumble.bee.app.models.dao.Entity;
import com.bumble.bee.app.models.dto.Dto;
import com.bumble.bee.app.service.IService;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.lang.NonNull;

import java.util.*;

@Slf4j
public abstract class AbstractRestService<KEY, DTO extends Dto<KEY>, ENTITY extends Entity<KEY>> implements RestService<KEY, DTO> {

    private final IService<ENTITY, KEY> service;
    private final IConverter<KEY, DTO, ENTITY> mapper;

    public AbstractRestService(IService<ENTITY, KEY> service, Class<? extends IConverter<KEY, DTO, ENTITY>> converter) {
        this.service = service;
        this.mapper = Mappers.getMapper(converter);
    }

    protected DTO convertToDto(ENTITY entity) {
        return this.mapper.toDto(entity);
    }

    protected ENTITY convertToEntity(DTO dto) {
        return this.mapper.toEntity(dto);
    }

    protected Collection<DTO> convertToDtoList(@NonNull Collection<ENTITY> entities) {
        return entities.stream().map(this::convertToDto)
                       .toList();
    }

    protected Collection<ENTITY> convertToEntityList(@NonNull Collection<DTO> dtos) {
        return dtos.stream().map(this::convertToEntity)
                   .toList();
    }

    @Override
    public DTO create(DTO dto) {
        log.info("[{}] Creating entity", this.getEntityClass().getSimpleName());
        var entity = this.convertToEntity(dto);
        var result = this.convertToDto(this.service.create(entity));
        log.info("[{}] Entity with id: {} created successfully", this.getEntityClass().getSimpleName(), result.getId());
        return result;
    }

    @Override
    public DTO update(KEY id, DTO dto) {
        log.info("[{}] Updating entity with id: {}", this.getEntityClass().getSimpleName(), id);
        var byId = this.findById(id);
        var entity = this.convertToEntity(dto);
        entity.setId(byId.getId());
        var result = this.convertToDto(this.service.update(entity));
        log.info("[{}] Entity with id: {} updated successfully", this.getEntityClass().getSimpleName(), result.getId());
        return result;
    }

    @Override
    public void delete(KEY key) {
        log.info("[{}] Deleting entity with id: {}", this.getEntityClass().getSimpleName(), key);
        this.findById(key);
        this.service.delete(key);
    }

    @Override
    public DTO findById(KEY key) {
        log.info("[{}] Finding entity with id: {}", this.getEntityClass().getSimpleName(), key);
        return this.service.findById(key).map(this::convertToDto)
                           .orElseThrow(() -> this.entityNotFoundException(key));
    }

    @Override
    public Collection<DTO> getAll() {
        log.info("[{}] Getting all entities", this.getEntityClass().getSimpleName());
        return this.service.getAll().stream().map(this::convertToDto)
                           .toList();
    }

    protected EntityNotFoundException entityNotFoundException(KEY key) {
        return new EntityNotFoundException(key, this.getEntityClass());
    }

    abstract protected Class<ENTITY> getEntityClass();
}
