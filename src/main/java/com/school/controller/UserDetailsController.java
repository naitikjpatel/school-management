package com.school.controller;

import com.school.dtos.UserDetailsDto;
import com.school.entity.UserDetails;
import com.school.mapper.UserDetailsMapper;
import com.school.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usersDetails/")
public class UserDetailsController {

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("addUserDetails")
    public ResponseEntity<UserDetailsDto> addUserDetails(@RequestBody UserDetailsDto userDetailsDto) {
        UserDetails userDetails = UserDetailsMapper.toEntity(userDetailsDto);
        //here converting Dto to Object
        userDetails = userDetailsService.addUserDetails(userDetails);
        if (userDetails != null) {
            //here converting Object to Dto
            userDetailsDto = UserDetailsMapper.toDto(userDetails);
            return new ResponseEntity<>(userDetailsDto, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);


    }

    @PutMapping("updateUserDetails")
    public ResponseEntity<UserDetailsDto> updateUserDetails(@RequestBody UserDetailsDto userDetailsDto) {
        UserDetails userDetails = UserDetailsMapper.toEntity(userDetailsDto);
        userDetails = userDetailsService.updateUserDetails(userDetails);

        if (userDetails != null) {
            userDetailsDto = UserDetailsMapper.toDto(userDetails);
            return new ResponseEntity<>(userDetailsDto, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


//    @DeleteMapping("deleteUserDetails")
//    public ResponseEntity<String> deleteUserDetails(@RequestBody UserDetails userDetails) {
//        userDetails=userDetailsService.deleteUserDetails(userDetails);
//        if(userDetails!=null) {
//            System.out.println("deleteUserDetails");
//            return new ResponseEntity<>("Deleted", HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }

    @GetMapping("getAllUserDetails")
    public ResponseEntity<List<UserDetailsDto>> getAllUserDetails() {
        List<UserDetails> userDetailsList = userDetailsService.getAllUserDetails();


        if (userDetailsList != null) {
            List<UserDetailsDto> userDetailsDtoList = userDetailsList.stream()
                    .map(UserDetailsMapper::toDto)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(userDetailsDtoList, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
