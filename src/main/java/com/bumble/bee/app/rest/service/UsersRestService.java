package com.bumble.bee.app.rest.service;

import com.bumble.bee.app.convertors.UserMapper;
import com.bumble.bee.app.exceptions.EntityNotFoundException;
import com.bumble.bee.app.models.dao.User;
import com.bumble.bee.app.models.dto.CredentialsDto;
import com.bumble.bee.app.models.dto.UserDto;
import com.bumble.bee.app.rest.AbstractRestService;
import com.bumble.bee.app.service.IService;
import com.bumble.bee.app.service.LoginService;
import com.bumble.bee.app.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersRestService extends AbstractRestService<Long, UserDto, User> {

    @Autowired
    LoginService loginService;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    public UsersRestService(IService<User, Long> service) {
        super(service, UserMapper.class);
    }

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

    public UserDto login(CredentialsDto dto) {
        User user = loginService.login(dto);
        if(user==null){
           return  null;
        }else{
            return userMapper.toDto(user);
        }
    }

}
