package com.bumble.bee.app.convertors;

import com.bumble.bee.app.models.dao.User;
import com.bumble.bee.app.models.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends IConverter<Long, UserDto, User> {

    @Override
    UserDto toDto(User entity);

    @Override
    User toEntity(UserDto dto);
}
