package com.school.mapper;

import com.school.dtos.UsersDtoForResult;
import com.school.entity.Users;
import org.springframework.beans.BeanUtils;

public class UsersDtoForResultMapper {
    public static Users toEntity(UsersDtoForResult usersDtoForResult) {
        Users users = new Users();
        BeanUtils.copyProperties(usersDtoForResult, users);
        //in user, we have an object to we also need to covert dto into object
        return users;
    }
    public static UsersDtoForResult toDto(Users users) {
        UsersDtoForResult usersDtoForResult = new UsersDtoForResult();
        BeanUtils.copyProperties(users, usersDtoForResult);
        //here to get the UsersDtoForResult we need to convert our object into the dto: above line done the for the provided Object/Entity : but if it has the nested entity then we need to do manually.

        return usersDtoForResult;
    }

}
