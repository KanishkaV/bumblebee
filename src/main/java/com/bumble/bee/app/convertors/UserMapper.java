package com.bumble.bee.app.convertors;

import com.bumble.bee.app.models.dao.User;
import com.bumble.bee.app.models.dto.FullUserDto;
import com.bumble.bee.app.models.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper extends IConverter<Long, FullUserDto, User> {


    @Override
    FullUserDto toDto(User entity);


    @Override
    User toEntity(FullUserDto dto);

    UserDto toComapctUserDto(User entity);
    @Mapping(target = "userName", ignore = true)
    @Mapping(target = "password", ignore = true)
    User comapctUserToEntity(UserDto dto);
}
