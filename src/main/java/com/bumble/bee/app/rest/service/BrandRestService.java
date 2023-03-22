package com.bumble.bee.app.rest.service;

import com.bumble.bee.app.convertors.BrandMapper;
import com.bumble.bee.app.models.dao.Brand;
import com.bumble.bee.app.models.dto.BrandDto;
import com.bumble.bee.app.rest.AbstractRestService;
import com.bumble.bee.app.service.IService;
import org.springframework.stereotype.Service;

@Service
public class BrandRestService extends AbstractRestService<Long, BrandDto, Brand> {

    public BrandRestService(IService<Brand, Long> service) {
        super(service, BrandMapper.class);
    }

    @Override
    protected Class<Brand> getEntityClass() {
        return Brand.class;
    }
}
