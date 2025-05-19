package com.school.mapper;

import com.school.dtos.UserDetailsDto;
import com.school.entity.UserDetails;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;

public class UserDetailsMapper {

    public static UserDetails toEntity(UserDetailsDto dto) {
        if (dto == null) {
            return null;
        }

        UserDetails entity = new UserDetails();
        entity.setUserDetailId(dto.getUserDetailId());
        entity.setDetails(dto.getDetails());
        entity.setAddress(dto.getAddress());
        entity.setPhone(dto.getPhone());
        // Map other fields as necessary

        return entity;
    }

    public static UserDetailsDto toDto(UserDetails entity) {
        if (entity == null) {
            return null;
        }

        UserDetailsDto dto = new UserDetailsDto();
        dto.setUserDetailId(entity.getUserDetailId());
        dto.setDetails(entity.getDetails());
        dto.setAddress(entity.getAddress());
        dto.setPhone(entity.getPhone());
        // Map other fields as necessary

        return dto;
    }
}
