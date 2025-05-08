package com.school.mapper;

import com.school.dtos.CourseDto;
import com.school.dtos.ResultDtoForUsers;
import com.school.dtos.UsersDto;
import com.school.entity.Users;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class UsersMapper    {

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

        // Manually map UserType and UserDetails (they're simple enough)
        usersDto.setUserType(users.getUserType());        // Optional: make a DTO if needed
        usersDto.setUserDetails(users.getUserDetails());  // Optional: make a DTO if needed

        // Copy results if needed, or ignore if unnecessary in response
        //here we getting the list of result so we neeed to convert that all the result enitity to dto
        List<ResultDtoForUsers> usersDtos=users.getResults().stream()
                        .map(ResultDtoForUsersMapper::toDto)
                                .collect(Collectors.toList());


        usersDto.setResults(usersDtos);
        // Map nested courses → courseDto
        List<CourseDto> courseDtos = users.getCourses().stream()
                .map(CourseMapper::toDto)
                .collect(Collectors.toList());

        usersDto.setCourses(courseDtos);

        return usersDto;
    }

}
