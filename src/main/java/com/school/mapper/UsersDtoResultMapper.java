package com.school.mapper;

import com.school.dtos.UsersDtoForResult;
import com.school.entity.Users;
import org.springframework.beans.BeanUtils;

public class UsersDtoResultMapper {
    public static Users toEntity(UsersDtoForResult usersDtoForResult) {
        Users users = new Users();
        BeanUtils.copyProperties(usersDtoForResult, users);
        return users;
    }
    public static UsersDtoForResult toDto(Users users) {
        UsersDtoForResult usersDtoForResult = new UsersDtoForResult();
        BeanUtils.copyProperties(users, usersDtoForResult);
        return usersDtoForResult;
    }

}
