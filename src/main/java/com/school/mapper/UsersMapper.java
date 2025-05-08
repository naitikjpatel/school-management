package com.school.mapper;

import com.school.dtos.CourseDto;
import com.school.dtos.ResultDtoForUsers;
import com.school.dtos.UsersDto;
import com.school.entity.Users;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class UsersMapper {

    public static Users toEntity(UsersDto usersDto) {
        Users users = new Users();
        BeanUtils.copyProperties(usersDto, users);
        return users;
    }

//    public static UsersDto toDto(Users users) {
//        UsersDto usersDto = new UsersDto();
//        BeanUtils.copyProperties(users, usersDto);
//
//        return usersDto;
//    }

//    new

    public static UsersDto toDto(Users users) {
        UsersDto usersDto = new UsersDto();

        // Copy basic fields
        usersDto.setUserId(users.getUserId());
        usersDto.setFirstName(users.getFirstName());
        usersDto.setLastName(users.getLastName());
        usersDto.setEmail(users.getEmail());

        //  map UserType and UserDetails
        usersDto.setUserType(users.getUserType());
        usersDto.setUserDetails(users.getUserDetails());

        // Copy results if needed, or ignore if unnecessary in response
        //here we're getting the list of result so we need to convert that all the result entity to dto
        List<ResultDtoForUsers> usersDtos = users.getResults().stream()
                .map(ResultDtoForUsersMapper::toDto)
                .collect(Collectors.toList());


        usersDto.setResults(usersDtos);
        // Map nested courses -> courseDto
        List<CourseDto> courseDtos = users.getCourses().stream()
                .map(CourseMapper::toDto)
                .collect(Collectors.toList());

        usersDto.setCourses(courseDtos);

        return usersDto;
    }

}
