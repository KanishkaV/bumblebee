package com.bumble.bee.app.service;

import com.bumble.bee.app.convertors.UserMapper;
import com.bumble.bee.app.models.dao.User;
import com.bumble.bee.app.models.dto.CredentialsDto;
import com.bumble.bee.app.models.dto.UserDto;
import com.bumble.bee.app.repository.UserRepository;
import lombok.extern.java.Log;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;
    private UserMapper mapper;

    public LoginService(){
        mapper= Mappers.getMapper(UserMapper.class);
    }
    public User login(CredentialsDto credentialsDto){
        return userRepository.login(credentialsDto.getUserName(), credentialsDto.getPassword());

    }
}
