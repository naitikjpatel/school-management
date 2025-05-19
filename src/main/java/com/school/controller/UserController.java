package com.school.controller;

import com.school.constants.ApiConstants;
import com.school.dtos.LoginRequest;
import com.school.dtos.UserResultDto;
import com.school.dtos.UsersDto;
import com.school.entity.Users;
import com.school.mapper.UserDetailsMapper;
import com.school.mapper.UserTypeMapper;
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
@CrossOrigin(origins = "http://localhost:3000")
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

    @PutMapping(ApiConstants.UPDATE_USER_BY_ID)
    public ResponseEntity<UsersDto> updateUser(@PathVariable Long userId, @RequestBody UsersDto userDto) {
        logger.info("Received request to update user with ID: {}", userId);

        // Fetch the existing user by ID
        Users existingUser = userService.getUserById(userId);
        if (existingUser == null) {
            logger.error("User not found with ID: {}", userId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Update the fields of the existing user with the new data
        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setLastName(userDto.getLastName());
        existingUser.setEmail(userDto.getEmail());
        // Update other fields as necessary

        // Optionally, update related entities like UserDetails or UserType
        if (userDto.getUserDetails() != null) {

            existingUser.setUserDetails(userDto.getUserDetails());
        }
        if (userDto.getUserType() != null) {
            existingUser.setUserType(userDto.getUserType());
        }

        // Save the updated user
        Users updatedUser = userService.saveUser(existingUser);

        logger.info("User updated successfully with ID: {}", updatedUser.getUserId());
        return new ResponseEntity<>(UsersMapper.toDto(updatedUser), HttpStatus.OK);
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


    @GetMapping("/{userId}/results")
    public ResponseEntity<List<UserResultDto>> getUserResults(@PathVariable Long userId) {
        List<UserResultDto> results = userService.getUserResults(userId);
        return ResponseEntity.ok(results);
    }

    @PostMapping("/login")
    public ResponseEntity<UsersDto> loginRequest(@RequestBody LoginRequest loginRequest){
        System.out.println(loginRequest.getUserId()+" : "+loginRequest.getEmail());
        Users users=userService.authenticate(loginRequest);
        if (users != null) {
            System.out.println("Login successful");
            logger.info("User found: {}", users);
            return new ResponseEntity<>(UsersMapper.toDto(users), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
