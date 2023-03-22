package com.bumble.bee.app.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ProductCategoryDto implements Dto<Long> {

    @NotNull(groups = {ValidationGroups.Update.class}, message = "Product Category Id is mandatory")
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @Size(min = 3, max = 512, message = "Description must be between 3 and 512 characters")
    private String description;

    private Collection<ProductDto> products;
}
