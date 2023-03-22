package com.bumble.bee.app.rest.service;

import com.bumble.bee.app.convertors.ProductMapper;
import com.bumble.bee.app.models.dao.Product;
import com.bumble.bee.app.models.dto.ProductDto;
import com.bumble.bee.app.rest.AbstractRestService;
import com.bumble.bee.app.service.IService;
import org.springframework.stereotype.Service;

@Service
public class ProductsRestService extends AbstractRestService<Long, ProductDto, Product> {

    public ProductsRestService(IService<Product, Long> service) {
        super(service, ProductMapper.class);
    }

    @Override
    protected Class<Product> getEntityClass() {
        return Product.class;
    }
}
