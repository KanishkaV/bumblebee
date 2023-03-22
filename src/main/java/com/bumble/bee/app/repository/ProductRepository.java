package com.bumble.bee.app.repository;

import com.bumble.bee.app.models.dao.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends IRepository<Product, Long> {

}
