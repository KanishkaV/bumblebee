package com.bumble.bee.app.convertors;

import com.bumble.bee.app.models.dao.Product;
import com.bumble.bee.app.models.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ProductMapper extends IConverter<Long, ProductDto, Product> {

    @Mappings({
            @Mapping(target = "brand.products", ignore = true),
            @Mapping(target = "category.products", ignore = true)
    })
    @Override
    ProductDto toDto(Product entity);

    @Override
    Product toEntity(ProductDto dto);
}
