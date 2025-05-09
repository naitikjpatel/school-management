package com.school.controller;

import com.school.constants.ApiConstants;
import com.school.dtos.UsersDto;
import com.school.entity.Users;
import com.school.mapper.UsersMapper;
import com.school.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ApiConstants.USERS)
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    // Add User
    @PostMapping(ApiConstants.ADD_USER)
    public ResponseEntity<UsersDto> addUser(@Valid @RequestBody UsersDto userDto) {
        logger.info("Received request to add new user: {}", userDto);

        Users user = UsersMapper.toEntity(userDto);
        Users createdUser = userService.addUser(user);

        if (createdUser != null) {
            logger.info("User created successfully with ID: {}", createdUser.getUserId());
            return new ResponseEntity<>(UsersMapper.toDto(createdUser), HttpStatus.CREATED);
        } else {
            logger.error("Failed to create user: {}", userDto);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Get All Users
    @GetMapping(ApiConstants.GET_ALL_USERS)
    public ResponseEntity<List<UsersDto>> getAllUser() {
        logger.info("Received request to fetch all users.");

        List<Users> allUsers = userService.getAllUsers();

        if (!allUsers.isEmpty()) {
            List<UsersDto> userDtos = allUsers.stream().map(UsersMapper::toDto).collect(Collectors.toList());
            logger.info("Fetched {} users.", userDtos.size());
            return new ResponseEntity<>(userDtos, HttpStatus.OK);
        } else {
            logger.warn("No users found.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    // Delete User by ID
    @DeleteMapping(ApiConstants.DELETE_USER_BY_ID)
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        logger.info("Received request to delete user with ID: {}", userId);

        Users deletedUser = userService.deleteUserById(userId);

        if (deletedUser != null) {
            logger.info("User deleted successfully with ID: {}", userId);
            return new ResponseEntity<>(UsersMapper.toDto(deletedUser), HttpStatus.OK);
        }

        logger.error("User with ID {} not found for deletion.", userId);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("User with ID " + userId + " not found");
    }

    // Update User
    @PutMapping(ApiConstants.UPDATE_USER)
    public ResponseEntity<UsersDto> updateUser(@RequestBody UsersDto userDto) {
        logger.info("Received request to update user: {}", userDto);

        Users user = UsersMapper.toEntity(userDto);
        Users updatedUser = userService.updateUser(user);

        if (updatedUser != null) {
            logger.info("User updated successfully with ID: {}", updatedUser.getUserId());
            return new ResponseEntity<>(UsersMapper.toDto(updatedUser), HttpStatus.OK);
        }

        logger.error("Failed to update user: {}", userDto);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // Get User by ID
    @GetMapping(ApiConstants.USER_BY_ID)
    public ResponseEntity<UsersDto> getUserById(@PathVariable Long userId) {
        logger.info("Received request to fetch user with ID: {}", userId);

        Users user = userService.getUserById(userId);

        if (user != null) {
            logger.info("User found: {}", user);
            return new ResponseEntity<>(UsersMapper.toDto(user), HttpStatus.OK);
        }

        logger.error("User with ID {} not found.", userId);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
