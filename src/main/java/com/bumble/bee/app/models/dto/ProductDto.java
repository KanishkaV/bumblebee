package com.bumble.bee.app.models.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductDto implements Dto<Long> {

    @NotNull(groups = {ValidationGroups.Update.class}, message = "Brand Id is mandatory")
    private Long id;
    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @Size(min = 3, max = 512, message = "Description must be between 3 and 512 characters")
    private String description;

    private byte[] image;

    @NotNull(message = "Price is mandatory")
    @Min(value = 0, message = "Price must be greater than 0")
    private Double price;

    @NotNull(message = "Category is mandatory")
    private ProductCategoryDto category;

    @NotNull(message = "Brand is mandatory")
    private BrandDto brand;
}
