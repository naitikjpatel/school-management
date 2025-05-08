package com.school.controller;

import com.school.constants.ApiConstants;
import com.school.dtos.UsersDto;
import com.school.entity.Users;
import com.school.mapper.UsersMapper;
import com.school.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ApiConstants.USERS)
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(ApiConstants.ADD_USER)
    public ResponseEntity<UsersDto> addUser(@RequestBody UsersDto userDto) {
        Users user = UsersMapper.toEntity(userDto);
        Users createdUser = userService.addUser(user);

        if (createdUser != null) {
            return new ResponseEntity<>(UsersMapper.toDto(createdUser), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(ApiConstants.GET_ALL_USERS)
    public ResponseEntity<List<UsersDto>> getAllUser() {
        List<Users> allUsers = userService.getAllUsers();

        if (!allUsers.isEmpty()) {
            List<UsersDto> userDtos = allUsers.stream().map(UsersMapper::toDto).collect(Collectors.toList());
            return new ResponseEntity<>(userDtos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping(ApiConstants.DELETE_USER_BY_ID)
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        Users deletedUser = userService.deleteUserById(userId);

        if (deletedUser != null) {
            return new ResponseEntity<>(UsersMapper.toDto(deletedUser), HttpStatus.OK);
        }

        // Custom message for 404
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("User with ID " + userId + " not found");
    }


    @PutMapping(ApiConstants.UPDATE_USER)
    public ResponseEntity<UsersDto> updateUser(@RequestBody UsersDto userDto) {
        Users user = UsersMapper.toEntity(userDto);
        Users updatedUser = userService.updateUser(user);

        if (updatedUser != null) {
            return new ResponseEntity<>(UsersMapper.toDto(updatedUser), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(ApiConstants.USER_BY_ID)
    public ResponseEntity<UsersDto> getUserById(@PathVariable Long userId) {
        Users user = userService.getUserById(userId);

        if (user != null) {
            return new ResponseEntity<>(UsersMapper.toDto(user), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
