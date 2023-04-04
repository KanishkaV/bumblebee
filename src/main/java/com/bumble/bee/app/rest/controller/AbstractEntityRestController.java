package com.bumble.bee.app.rest.controller;

import com.bumble.bee.app.models.dao.Entity;
import com.bumble.bee.app.models.dto.Dto;
import com.bumble.bee.app.models.dto.ValidationGroups;
import com.bumble.bee.app.rest.AbstractRestService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Getter(AccessLevel.PROTECTED)
public abstract class AbstractEntityRestController<KEY, DTO extends Dto<KEY>, ENTITY extends Entity<KEY>> {

    private final AbstractRestService<KEY, DTO, ENTITY> restService;

    public AbstractEntityRestController(AbstractRestService<KEY, DTO, ENTITY> restService) {
        this.restService = restService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<DTO>> findAll() {
        return ResponseEntity.ok(this.getRestService().getAll());
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<DTO> findById(@PathVariable KEY id) {
        return ResponseEntity.ok(this.getRestService().findById(id));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createEntity(@Valid @RequestBody DTO dto) {
        DTO dto1 = null;
        try {
             dto1 = this.getRestService().create(dto);
        }catch (DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body("UserName already in use");
        }
        return ResponseEntity.ok(dto1);
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<DTO> updateEntity(@Validated(ValidationGroups.Update.class) @RequestBody DTO dto) {
        return ResponseEntity.ok(this.getRestService().update(dto.getId(), dto));
    }

    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteEntity(@PathVariable KEY id) {
        this.getRestService().delete(id);
        return ResponseEntity.accepted().build();
    }
}
