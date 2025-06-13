package com.school.service;

import com.school.Exception.ResourceNotFoundException;

import com.school.dtos.LoginRequest;
import com.school.dtos.UserResultDto;
import com.school.entity.Course;
import com.school.entity.UserDetails;
import com.school.entity.UserType;
import com.school.entity.Users;
import com.school.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UserDetailsRepository userDetailsRepository;
    @Autowired
    private UserTypeRepository userTypeRepository;


    //Add User Service
    public Users addUser(Users user) {
        //userDetails saved
        UserDetails userDetails = userDetailsRepository.save(user.getUserDetails());
//        UserType userType = userTypeRepository.findById(user.getUserType().getUserTypeId()).orElseThrow(() -> new ResourceNotFoundException("UserType Not Found"));
        if (user.getUserType().getUserTypeId() != null) {
            //here we get the userTypeId
            UserType userType = userTypeRepository.findById(user.getUserType().getUserTypeId())
                    .orElseThrow(() -> new ResourceNotFoundException("UserType Not Found"));

            user.setUserType(userType);
        } else {
            throw new ResourceNotFoundException("UserType is not provided or not found");
        }
        user.setUserDetails(userDetails);
        return usersRepository.save(user);
    }

    //Get Users by I'd Service
    public Users getUserById(Long id) {
        Users users = usersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found With Id: " + id));
        return users;

    }

    //Delete User By I'd Service
    public boolean deleteUserById(Long id) {
        Optional<Users> usersOptional = usersRepository.findById(id);
        if (usersOptional.isPresent()) {
//            userCourseRepository.deleteByUser_UserId(usersOptional.get().getUserId());
//            userCourseRepository.deleteByUserId(usersOptional.get().getUserId());
            usersRepository.delete(usersOptional.get());
            return true;
        }
        return false;

    }

    //Update User Service
    public Users updateUser(Users newUser) {

        Users existingUser = usersRepository.findById(newUser.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + newUser.getUserId()));

        // Update fields
        if (newUser.getFirstName() != null) {
            existingUser.setFirstName(newUser.getFirstName());
        }
        if (newUser.getLastName() != null) {
            existingUser.setLastName(newUser.getLastName());
        }
        if (newUser.getEmail() != null) {
            existingUser.setEmail(newUser.getEmail());
        }

        // Update UserDetails if provided
        if (newUser.getUserDetails() != null) {
            UserDetails existingUserDetails = existingUser.getUserDetails();
            UserDetails newUserDetails = newUser.getUserDetails();

            if (newUserDetails.getDetails() != null) {
                existingUserDetails.setDetails(newUserDetails.getDetails());
            }
            if (newUserDetails.getAddress() != null) {
                existingUserDetails.setAddress(newUserDetails.getAddress());
            }
            if (newUserDetails.getPhone() != null) {
                existingUserDetails.setPhone(newUserDetails.getPhone());
            }

            // Save updated UserDetails
            userDetailsRepository.save(existingUserDetails);
        }

        // Update UserType if provided
        if (newUser.getUserType() != null) {
            existingUser.setUserType(newUser.getUserType());
        }
        return usersRepository.save(existingUser);
    }

    //Get All Users Service
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }


    public Users saveUser(Users user) {
        return usersRepository.save(user);
    }


    public List<UserResultDto> getUserResults(Long userId) {
        return usersRepository.findUserResults(userId);
    }

    public Users authenticate(LoginRequest loginRequest) {
        return usersRepository.findByUserIdAndEmail(loginRequest.getUserId(), loginRequest.getEmail()).orElse(null);
    }

}
