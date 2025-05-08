package com.school.service;

import com.school.Exception.ResourceNotFoundException;
import com.school.entity.ExamType;
import com.school.entity.UserType;
import com.school.repository.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTypeService {

    @Autowired
    private UserTypeRepository userTypeRepository;

    //Add UserType Service
    public UserType addUserType(UserType userType) {
        return userTypeRepository.save(userType);
    }

    //Update UserType Service
    public UserType updateUserType(UserType userType) {


        UserType existingUserType = userTypeRepository.findById(userType.getUserTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("UserType not found with id: " + userType.getUserTypeId()));
        if (userType.getUserTypes() != null) {
            existingUserType.setUserTypes(userType.getUserTypes());
            return userTypeRepository.save(existingUserType);

        }

        return null;
    }

    //Get All UserType Service
    public List<UserType> getAllUserType() {
        return userTypeRepository.findAll();
    }

    //Get UserType By Id Service
    public UserType getUserTypeById(Long id) {
        UserType userType = userTypeRepository.findById(id).orElse(null);
        return userType;
    }

    //Delete UserType By Id Service
    public UserType deleteUserTypeById(Long id) {
        UserType userType = userTypeRepository.findById(id).orElse(null);
        if (userType != null) {
            userTypeRepository.delete(userType);
        }
        return userType;
    }
}
