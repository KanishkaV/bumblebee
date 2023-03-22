package com.bumble.bee.app.rest;

import com.bumble.bee.app.models.dto.Dto;

import java.util.*;

public interface RestService<KEY, DTO extends Dto<KEY>> {

    DTO create(DTO dto);

    DTO update(KEY id, DTO dto);

    void delete(KEY key);

    DTO findById(KEY key);

    Collection<DTO> getAll();

}
