package com.bakulin.trialtaskquotes.service.implementation;

import com.bakulin.trialtaskquotes.dto.UserDto;
import com.bakulin.trialtaskquotes.entity.Users;
import com.bakulin.trialtaskquotes.exception.EqualsPasswordsException;
import com.bakulin.trialtaskquotes.mapper.UserDtoMapper;
import com.bakulin.trialtaskquotes.repository.UserRepository;
import com.bakulin.trialtaskquotes.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;

    public UserServiceImpl(UserRepository userRepository, UserDtoMapper userDtoMapper) {
        this.userRepository = userRepository;
        this.userDtoMapper = userDtoMapper;
    }

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * creating profile of user
     */
    public Users createProfile(UserDto userDto) {
        if (userRepository.findUsersByPassword(userDto.getPassword()) != null) {
            throw new EqualsPasswordsException("this password is already exist!");
        } else {
            Users user = userDtoMapper.userDtoToUsers(userDto);
            user.setDateOfCreation(OffsetDateTime.now());
            logger.info("createUser service method in work");
            return userRepository.save(user);
        }

    }
}
