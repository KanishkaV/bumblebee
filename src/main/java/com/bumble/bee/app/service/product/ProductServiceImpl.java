package com.bumble.bee.app.service.product;

import com.bumble.bee.app.models.dao.Product;
import com.bumble.bee.app.repository.ProductRepository;
import com.bumble.bee.app.service.AbstractService;
import com.bumble.bee.app.service.brand.BrandService;
import com.bumble.bee.app.service.category.ProductCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Service
public class ProductServiceImpl extends AbstractService<Product, Long> implements ProductService {

    private final BrandService brandService;
    private final ProductCategoryService categoryService;

    @Autowired
    public ProductServiceImpl(ProductRepository repository, BrandService brandService, ProductCategoryService categoryService) {
        super(repository);
        this.brandService = brandService;
        this.categoryService = categoryService;
    }

    @Override
    protected Class<Product> getEntityClass() {
        return Product.class;
    }

    @Override
    public Product create(Product entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getBrand()) || Objects.isNull(entity.getCategory())) {
            throw new IllegalArgumentException(String.format("'%s' entity cannot be null", this.getEntityClass().getSimpleName()));
        }
        var brand = this.brandService.findById(entity.getBrand().getId())
                                     .orElseThrow(() -> new IllegalArgumentException("Brand not found"));
        var category = this.categoryService.findById(entity.getCategory().getId())
                                           .orElseThrow(() -> new IllegalArgumentException("Category not found"));
        entity.setBrand(brand);
        entity.setCategory(category);
        return this.getRepository().save(entity);
    }

    @Override
    public Product update(Product entity) {
        var product = this.findById(entity.getId())
                          .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        entity.setId(product.getId());
        return this.create(entity);
    }

   /* @Override
    public Optional<Product> findById(Long id) {
        AtomicReference<Product> _product = new AtomicReference<>();
        this.getRepository().findById(id).ifPresent(product -> {
            var p = new Product();
            p.setId(product.getId());
            p.setName(product.getName());
            p.setPrice(product.getPrice());
            p.setImage(product.getImage());
            System.out.println(product);
            _product.set(p);
        });
        return Optional.ofNullable(_product.get());
    }*/
}
