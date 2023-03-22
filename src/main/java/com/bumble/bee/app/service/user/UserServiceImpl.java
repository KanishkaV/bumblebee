package com.bumble.bee.app.service.user;

import com.bumble.bee.app.models.dao.User;
import com.bumble.bee.app.repository.UserRepository;
import com.bumble.bee.app.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends AbstractService<User, Long> implements UserService {

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
    }

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }
}
