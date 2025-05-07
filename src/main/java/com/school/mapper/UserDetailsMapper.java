package com.school.mapper;

import com.school.dtos.UserDetailsDto;
import com.school.entity.UserDetails;
import org.springframework.beans.BeanUtils;

public class UserDetailsMapper {

    public static UserDetails toEntity(UserDetailsDto userDetailsDto) {
        UserDetails userDetails = new UserDetails();
        BeanUtils.copyProperties(userDetailsDto, userDetails);
        return userDetails;
    }

    public static UserDetailsDto toDto(UserDetails userDetails) {
        UserDetailsDto userDetailsDto = new UserDetailsDto();
        BeanUtils.copyProperties(userDetails, userDetailsDto);
        return userDetailsDto;
    }
}
