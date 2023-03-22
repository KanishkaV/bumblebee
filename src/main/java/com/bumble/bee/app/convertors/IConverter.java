package com.bumble.bee.app.convertors;

import com.bumble.bee.app.models.dao.Entity;
import com.bumble.bee.app.models.dto.Dto;

public interface IConverter<KEY, DTO extends Dto<KEY>, ENTITY extends Entity<KEY>> {

    DTO toDto(ENTITY entity);

    ENTITY toEntity(DTO dto);
}
