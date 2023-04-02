package com.bumble.bee.app.repository;

import com.bumble.bee.app.models.dao.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends IRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.userName = :userName AND u.password = :password")
    public User login(@Param("userName") String userName,@Param("password") String password);
}
