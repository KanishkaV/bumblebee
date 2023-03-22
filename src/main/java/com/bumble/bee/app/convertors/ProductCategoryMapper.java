package com.bumble.bee.app.convertors;

import com.bumble.bee.app.models.dao.ProductCategory;
import com.bumble.bee.app.models.dto.ProductCategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ProductCategoryMapper extends IConverter<Long, ProductCategoryDto, ProductCategory> {

    @Mappings({
            @Mapping(target = "products", ignore = true)
    })
    @Override
    ProductCategoryDto toDto(ProductCategory entity);

    @Override
    ProductCategory toEntity(ProductCategoryDto dto);
}
