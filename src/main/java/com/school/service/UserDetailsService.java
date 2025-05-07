package com.school.service;

import com.school.Exception.ResourceNotFoundException;
import com.school.entity.UserDetails;
import com.school.entity.Users;
import com.school.repository.UserDetailsRepository;
import com.school.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;
    @Autowired
    private UsersRepository usersRepository;

    public UserDetails addUserDetails(UserDetails userDetails) {
        //find the user
//        Users users=usersRepository.findById(userDetails.getUser().getUserId()).orElseThrow(()-> new ResourceNotFoundException("User Not Found With Id: " + userDetails.getUser().getUserId() ));

//        userDetails.setUser(users);
        return userDetailsRepository.save(userDetails);

    }

    public UserDetails updateUserDetails(UserDetails newUserDetails) {
        // First, check if the UserDetails with the given id exists.
        UserDetails existingUserDetails = userDetailsRepository.findById(newUserDetails.getUserDetailId())
                .orElseThrow(() -> new ResourceNotFoundException("UserDetails not found with id: " + newUserDetails.getUserDetailId()));

        // Update the fields of the existing UserDetails object
        if (newUserDetails.getDetails() != null) {
            existingUserDetails.setDetails(newUserDetails.getDetails());
        }
        if (newUserDetails.getAddress() != null) {
            existingUserDetails.setAddress(newUserDetails.getAddress());
        }
        if (newUserDetails.getPhone() != null) {
            existingUserDetails.setPhone(newUserDetails.getPhone());
        }

        // Save the updated UserDetails and return it
        return userDetailsRepository.save(existingUserDetails);
    }


//    public UserDetails deleteUserDetails(UserDetails userDetails){
//        UserDetails userDetails1 = userDetailsRepository.findById(userDetails.getUserDetailId())
//                .orElseThrow(() -> new ResourceNotFoundException("UserDetails Not Found With Id: " + userDetails.getUserDetailId()));
//
//
//        userDetailsRepository.delete(userDetails1);
//        System.out.println("user details deleted successfully"+userDetails1.getUserDetailId());
//        return userDetails1;
//    }

    public List<UserDetails> getAllUserDetails() {
        return userDetailsRepository.findAll();
    }
}
