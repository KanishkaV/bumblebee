package com.bumble.bee.app.convertors;

import com.bumble.bee.app.models.dao.User;
import com.bumble.bee.app.models.dto.FullUserDto;
import com.bumble.bee.app.models.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper extends IConverter<Long, UserDto, User> {


    @Override
    UserDto toDto(User entity);

    @Mapping(target = "userName", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Override
    User toEntity(UserDto dto);

    FullUserDto toFullUserDto(User entity);

    User fullUserToEntity(FullUserDto dto);
}
