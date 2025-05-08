package com.school.controller;

import com.school.constants.ApiConstants;
import com.school.dtos.UserTypeDto;
import com.school.entity.UserType;
import com.school.mapper.UserTypeMapper;
import com.school.service.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ApiConstants.USER_TYPE)
public class UserTypeController {

    @Autowired
    private UserTypeService userTypeService;

    @PostMapping(ApiConstants.ADD_USER_TYPE)
    public ResponseEntity<UserTypeDto> addUserType(@RequestBody UserTypeDto userTypeDto) {
        UserType userType = UserTypeMapper.toEntity(userTypeDto);
        UserType saved = userTypeService.addUserType(userType);

        if (saved != null) {
            return new ResponseEntity<>(UserTypeMapper.toDto(saved), HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(ApiConstants.UPDATE_USER_TYPE)
    public ResponseEntity<UserTypeDto> updateUserType(@RequestBody UserTypeDto userTypeDto) {
        UserType userType = UserTypeMapper.toEntity(userTypeDto);
        UserType updated = userTypeService.updateUserType(userType);

        if (updated != null) {
            return new ResponseEntity<>(UserTypeMapper.toDto(updated), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(ApiConstants.GET_ALL_USER_TYPES)
    public ResponseEntity<List<UserTypeDto>> getAllUserTypes() {
        List<UserType> userTypes = userTypeService.getAllUserType();

        if (!userTypes.isEmpty()) {
            List<UserTypeDto> dtoList = userTypes.stream()
                    .map(UserTypeMapper::toDto)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(dtoList, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(ApiConstants.USER_TYPE_BY_ID)
    public ResponseEntity<UserTypeDto> getUserTypeById(@PathVariable Long id) {
        UserType userType = userTypeService.getUserTypeById(id);

        if (userType != null) {
            return new ResponseEntity<>(UserTypeMapper.toDto(userType), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(ApiConstants.DELETE_USER_TYPE_BY_ID)
    public ResponseEntity<UserTypeDto> deleteUserType(@PathVariable Long id) {
        UserType deleted = userTypeService.deleteUserTypeById(id);

        if (deleted != null) {
            return new ResponseEntity<>(UserTypeMapper.toDto(deleted), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}


