package com.school.mapper;

import com.school.dtos.UserTypeDto;
import com.school.entity.UserType;
import org.springframework.beans.BeanUtils;

public class UserTypeMapper {

    public static UserType toEntity(UserTypeDto userTypeDto) {
        UserType userType = new UserType();
        BeanUtils.copyProperties(userTypeDto, userType);
        return userType;

    }
    public static UserTypeDto toDto(UserType userType) {
        UserTypeDto userTypeDto = new UserTypeDto();
        BeanUtils.copyProperties(userType, userTypeDto);
        return userTypeDto;
    }
}
