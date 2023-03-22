package com.bumble.bee.app.convertors;

import com.bumble.bee.app.models.dao.Brand;
import com.bumble.bee.app.models.dto.BrandDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface BrandMapper extends IConverter<Long, BrandDto, Brand> {

    @Mappings({
            @Mapping(target = "products", ignore = true)
    })
    @Override
    BrandDto toDto(Brand entity);

    @Mapping(target = "id", ignore = true)
    @Override
    Brand toEntity(BrandDto dto);
}
