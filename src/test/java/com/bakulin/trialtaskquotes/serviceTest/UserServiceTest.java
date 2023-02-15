package com.bakulin.trialtaskquotes.serviceTest;

import com.bakulin.trialtaskquotes.dto.UserDto;
import com.bakulin.trialtaskquotes.entity.Users;
import com.bakulin.trialtaskquotes.mapper.UserDtoMapper;
import com.bakulin.trialtaskquotes.repository.UserRepository;
import com.bakulin.trialtaskquotes.service.implementation.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserDtoMapper userDtoMapper;
    @InjectMocks
    private UserServiceImpl userServiceImpl;
   @Test
   void createUser_test(){
       Users USER = new Users(1L,"name","o@a.com","111",OffsetDateTime.now());
       UserDto userDto = new UserDto("name","o@a.com","111");
       when(userDtoMapper.userDtoToUsers(userDto)).thenReturn(USER);
       when(userRepository.save(any(Users.class))).thenReturn(USER);
       Users expectedUser = userServiceImpl.createProfile(userDto);
       assertEquals(expectedUser,USER);
   }

}
