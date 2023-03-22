package com.bumble.bee.app.rest.service;

import com.bumble.bee.app.convertors.UserMapper;
import com.bumble.bee.app.models.dao.User;
import com.bumble.bee.app.models.dto.UserDto;
import com.bumble.bee.app.rest.AbstractRestService;
import com.bumble.bee.app.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersRestService extends AbstractRestService<Long, UserDto, User> {

    @Autowired
    public UsersRestService(IService<User, Long> service) {
        super(service, UserMapper.class);
    }

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }
}
