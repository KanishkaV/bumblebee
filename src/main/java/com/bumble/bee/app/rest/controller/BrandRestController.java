package com.bumble.bee.app.rest.controller;

import com.bumble.bee.app.models.dao.Brand;
import com.bumble.bee.app.models.dto.BrandDto;
import com.bumble.bee.app.rest.AbstractRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/brands")
public class BrandRestController extends AbstractEntityRestController<Long, BrandDto, Brand> {

    @Autowired
    public BrandRestController(AbstractRestService<Long, BrandDto, Brand> restService) {
        super(restService);
    }
}
