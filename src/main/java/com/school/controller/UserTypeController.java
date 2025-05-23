package com.school.controller;

import com.school.constants.ApiConstants;
import com.school.dtos.UserTypeDto;
import com.school.entity.UserType;
import com.school.mapper.UserTypeMapper;
import com.school.service.UserTypeService;
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
@RequestMapping(ApiConstants.USER_TYPE)
public class UserTypeController {

    private static final Logger logger = LoggerFactory.getLogger(UserTypeController.class);

    @Autowired
    private UserTypeService userTypeService;

    @PostMapping(ApiConstants.ADD_USER_TYPE)
    public ResponseEntity<UserTypeDto> addUserType(@Valid @RequestBody UserTypeDto userTypeDto) {
        logger.info("Received request to add user type: {}", userTypeDto);

//        UserType userType = UserTypeMapper.toEntity(userTypeDto);
        UserType saved = userTypeService.addUserType(UserTypeMapper.toEntity(userTypeDto));

        if (saved != null) {
            logger.info("User type added successfully with ID: {}", saved.getUserTypeId());
            return new ResponseEntity<>(UserTypeMapper.toDto(saved), HttpStatus.CREATED);
        }

        logger.error("Failed to add user type: {}", userTypeDto);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(ApiConstants.UPDATE_USER_TYPE)
    public ResponseEntity<UserTypeDto> updateUserType(@RequestBody UserTypeDto userTypeDto) {
        logger.info("Received request to update user type: {}", userTypeDto);

        UserType userType = UserTypeMapper.toEntity(userTypeDto);
        UserType updated = userTypeService.updateUserType(userType);

        if (updated != null) {
            logger.info("User type updated successfully with ID: {}", updated.getUserTypeId());
            return new ResponseEntity<>(UserTypeMapper.toDto(updated), HttpStatus.OK);
        }

        logger.warn("User type not found for update: {}", userTypeDto);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(ApiConstants.GET_ALL_USER_TYPES)
    public ResponseEntity<List<UserTypeDto>> getAllUserTypes() {
        logger.info("Received request to fetch all user types.");

        List<UserType> userTypes = userTypeService.getAllUserType();

        if (!userTypes.isEmpty()) {
            List<UserTypeDto> dtoList = userTypes.stream()
                    .map(UserTypeMapper::toDto)
                    .collect(Collectors.toList());
            logger.info("Fetched {} user types.", dtoList.size());
            return new ResponseEntity<>(dtoList, HttpStatus.OK);
        }

        logger.warn("No user types found.");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(ApiConstants.USER_TYPE_BY_ID)
    public ResponseEntity<UserTypeDto> getUserTypeById(@PathVariable Long id) {
        logger.info("Received request to fetch user type with ID: {}", id);

        UserType userType = userTypeService.getUserTypeById(id);

        if (userType != null) {
            logger.info("User type found: {}", userType);
            return new ResponseEntity<>(UserTypeMapper.toDto(userType), HttpStatus.OK);
        }

        logger.warn("User type not found with ID: {}", id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(ApiConstants.DELETE_USER_TYPE_BY_ID)
    public ResponseEntity<UserTypeDto> deleteUserType(@PathVariable Long id) {
        logger.info("Received request to delete user type with ID: {}", id);

        UserType deleted = userTypeService.deleteUserTypeById(id);

        if (deleted != null) {
            logger.info("User type deleted successfully with ID: {}", id);
            return new ResponseEntity<>(UserTypeMapper.toDto(deleted), HttpStatus.OK);
        }

        logger.warn("User type not found for deletion with ID: {}", id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
