package com.bumble.bee.app.rest.service;

import com.bumble.bee.app.convertors.ProductCategoryMapper;
import com.bumble.bee.app.models.dao.ProductCategory;
import com.bumble.bee.app.models.dto.ProductCategoryDto;
import com.bumble.bee.app.rest.AbstractRestService;
import com.bumble.bee.app.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCategoryRestService extends AbstractRestService<Long, ProductCategoryDto, ProductCategory> {

    @Autowired
    public ProductCategoryRestService(IService<ProductCategory, Long> service) {
        super(service, ProductCategoryMapper.class);
    }

    @Override
    protected Class<ProductCategory> getEntityClass() {
        return ProductCategory.class;
    }
}
