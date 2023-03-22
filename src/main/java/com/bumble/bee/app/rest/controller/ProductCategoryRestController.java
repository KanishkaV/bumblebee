package com.bumble.bee.app.rest.controller;

import com.bumble.bee.app.models.dao.ProductCategory;
import com.bumble.bee.app.models.dto.ProductCategoryDto;
import com.bumble.bee.app.rest.AbstractRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/categories")
public class ProductCategoryRestController extends AbstractEntityRestController<Long, ProductCategoryDto, ProductCategory> {

    @Autowired
    public ProductCategoryRestController(AbstractRestService<Long, ProductCategoryDto, ProductCategory> restService) {
        super(restService);
    }
}
