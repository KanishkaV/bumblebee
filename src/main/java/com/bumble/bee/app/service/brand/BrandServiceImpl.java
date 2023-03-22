package com.bumble.bee.app.service.brand;

import com.bumble.bee.app.models.dao.Brand;
import com.bumble.bee.app.repository.IRepository;
import com.bumble.bee.app.service.AbstractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BrandServiceImpl extends AbstractService<Brand, Long> implements BrandService {

    @Autowired
    public BrandServiceImpl(IRepository<Brand, Long> repository) {
        super(repository);
    }

    @Override
    protected Class<Brand> getEntityClass() {
        return Brand.class;
    }
}
