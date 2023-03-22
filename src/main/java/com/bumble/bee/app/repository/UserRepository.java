package com.bumble.bee.app.repository;

import com.bumble.bee.app.models.dao.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends IRepository<User, Long> {

}
