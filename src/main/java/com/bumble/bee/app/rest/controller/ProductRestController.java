package com.bumble.bee.app.rest.controller;

import com.bumble.bee.app.models.dao.Product;
import com.bumble.bee.app.models.dto.ProductDto;
import com.bumble.bee.app.rest.AbstractRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/products")
public class ProductRestController extends AbstractEntityRestController<Long, ProductDto, Product> {

    @Autowired
    public ProductRestController(AbstractRestService<Long, ProductDto, Product> restService) {
        super(restService);
    }
}
