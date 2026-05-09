package com.otus.mapper;

import com.otus.dao.User;
import com.otus.model.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserDto from(User user);

  User from(UserDto user);
}
