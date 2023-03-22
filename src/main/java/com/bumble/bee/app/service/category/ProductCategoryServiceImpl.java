package com.bumble.bee.app.service.category;

import com.bumble.bee.app.models.dao.ProductCategory;
import com.bumble.bee.app.repository.IRepository;
import com.bumble.bee.app.service.AbstractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductCategoryServiceImpl extends AbstractService<ProductCategory, Long> implements ProductCategoryService {

    @Autowired
    public ProductCategoryServiceImpl(IRepository<ProductCategory, Long> repository) {
        super(repository);
    }

    @Override
    protected Class<ProductCategory> getEntityClass() {
        return ProductCategory.class;
    }
}
