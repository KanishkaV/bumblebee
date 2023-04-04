package com.bumble.bee.app.rest.service;

import com.bumble.bee.app.convertors.UserMapper;
import com.bumble.bee.app.models.dao.User;
import com.bumble.bee.app.models.dto.CredentialsDto;
import com.bumble.bee.app.models.dto.FullUserDto;
import com.bumble.bee.app.models.dto.UserDto;
import com.bumble.bee.app.rest.AbstractRestService;
import com.bumble.bee.app.service.IService;
import com.bumble.bee.app.service.LoginService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UsersRestService extends AbstractRestService<Long, FullUserDto, User> {

    @Autowired
    LoginService loginService;
     UserMapper userMapper;

    @Autowired
    public UsersRestService(IService<User, Long> service) {
        super(service, UserMapper.class);
        userMapper= Mappers.getMapper(UserMapper.class);
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
            return userMapper.toComapctUserDto(user);
        }
    }

    public Collection<UserDto> getALlUserDto(){
       return service.getAll().stream().map(userMapper::toComapctUserDto).collect(Collectors.toList());
    }

}
