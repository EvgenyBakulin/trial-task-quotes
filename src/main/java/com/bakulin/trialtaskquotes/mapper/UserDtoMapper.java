package com.bakulin.trialtaskquotes.mapper;

import com.bakulin.trialtaskquotes.dto.UserDto;
import com.bakulin.trialtaskquotes.entity.Users;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserDtoMapper {
    Users userDtoToUsers(UserDto userDto);

    UserDto usersToUserDto(Users user);
}
