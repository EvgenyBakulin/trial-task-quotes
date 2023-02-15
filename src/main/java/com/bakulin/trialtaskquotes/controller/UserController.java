package com.bakulin.trialtaskquotes.controller;

import com.bakulin.trialtaskquotes.dto.UserDto;
import com.bakulin.trialtaskquotes.entity.Users;
import com.bakulin.trialtaskquotes.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * creating profile of user
     */
    @PostMapping("/users")
    @Operation(summary = "creating profile of user", responses =
            {@ApiResponse(responseCode = "200", description = "Successful"), @ApiResponse(responseCode = "403", description = "Forbidden")})
    public ResponseEntity<Users> createProfile(UserDto userDto) {
        logger.info("createUser method in work");
        return ResponseEntity.ok(userService.createProfile(userDto));
    }
}
