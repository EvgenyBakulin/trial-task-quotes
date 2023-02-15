package com.bakulin.trialtaskquotes.service;

import com.bakulin.trialtaskquotes.dto.UserDto;
import com.bakulin.trialtaskquotes.entity.Users;

public interface UserService {
    Users createProfile(UserDto userDto);
}
