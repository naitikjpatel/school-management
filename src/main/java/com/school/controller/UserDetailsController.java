package com.school.controller;

import com.school.constants.ApiConstants;
import com.school.dtos.UserDetailsDto;
import com.school.entity.UserDetails;
import com.school.mapper.UserDetailsMapper;
import com.school.service.UserDetailsService;
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
@RequestMapping(ApiConstants.USER_DETAILS)
public class UserDetailsController {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsController.class);

    @Autowired
    private UserDetailsService userDetailsService;

    // Add UserDetails
    @PostMapping(ApiConstants.ADD_USER_DETAILS)
    public ResponseEntity<UserDetailsDto> addUserDetails(@Valid @RequestBody UserDetailsDto userDetailsDto) {
        logger.info("Received request to add user details: {}", userDetailsDto);

        UserDetails userDetails = UserDetailsMapper.toEntity(userDetailsDto);
        userDetails = userDetailsService.addUserDetails(userDetails);

        if (userDetails != null) {
            userDetailsDto = UserDetailsMapper.toDto(userDetails);
            logger.info("User details added successfully with ID: {}", userDetailsDto.getUserDetailId());
            return new ResponseEntity<>(userDetailsDto, HttpStatus.CREATED);
        } else {
            logger.error("Failed to add user details: {}", userDetailsDto);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Update UserDetails
    @PutMapping(ApiConstants.UPDATE_USER_DETAILS)
    public ResponseEntity<UserDetailsDto> updateUserDetails(@RequestBody UserDetailsDto userDetailsDto) {
        logger.info("Received request to update user details: {}", userDetailsDto);

        UserDetails userDetails = UserDetailsMapper.toEntity(userDetailsDto);
        userDetails = userDetailsService.updateUserDetails(userDetails);

        if (userDetails != null) {
            userDetailsDto = UserDetailsMapper.toDto(userDetails);
            logger.info("User details updated successfully with ID: {}", userDetailsDto.getUserDetailId());
            return new ResponseEntity<>(userDetailsDto, HttpStatus.OK);
        } else {
            logger.error("Failed to update user details: {}", userDetailsDto);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Get All UserDetails
    @GetMapping(ApiConstants.GET_ALL_USER_DETAILS)
    public ResponseEntity<List<UserDetailsDto>> getAllUserDetails() {
        logger.info("Received request to fetch all user details.");

        List<UserDetails> userDetailsList = userDetailsService.getAllUserDetails();

        if (userDetailsList != null && !userDetailsList.isEmpty()) {
            List<UserDetailsDto> userDetailsDtoList = userDetailsList.stream()
                    .map(UserDetailsMapper::toDto)
                    .collect(Collectors.toList());
            logger.info("Fetched {} user details.", userDetailsDtoList.size());
            return new ResponseEntity<>(userDetailsDtoList, HttpStatus.OK);
        } else {
            logger.warn("No user details found.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
