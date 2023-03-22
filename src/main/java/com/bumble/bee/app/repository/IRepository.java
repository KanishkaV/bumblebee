package com.bumble.bee.app.repository;

import com.bumble.bee.app.models.dao.Entity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IRepository<ENTITY extends Entity<KEY>, KEY>
        extends CrudRepository<ENTITY, KEY>, ListPagingAndSortingRepository<ENTITY, KEY> {
}
