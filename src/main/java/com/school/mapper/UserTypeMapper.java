package com.school.mapper;

import com.school.dtos.UserTypeDto;
import com.school.entity.UserType;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;

public class UserTypeMapper {

    public static UserType toEntity( UserTypeDto userTypeDto) {
        UserType userType = new UserType();
//        BeanUtils.copyProperties(userTypeDto, userType);
        userType.setUserTypeId(userTypeDto.getUserTypeId());
        userType.setUserTypes(userTypeDto.getUserTypes());
        return userType;

    }
    public static UserTypeDto toDto(UserType userType) {
        UserTypeDto userTypeDto = new UserTypeDto();
//        BeanUtils.copyProperties(userType, userTypeDto);
        userTypeDto.setUserTypeId(userType.getUserTypeId());
        userTypeDto.setUserTypes(userType.getUserTypes());
        return userTypeDto;

    }
}
