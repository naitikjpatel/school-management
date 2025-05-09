package com.school.dtos;

import com.school.entity.Course;
import com.school.entity.Result;
import com.school.entity.UserDetails;
import com.school.entity.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UsersDto {

    Long userId;

    @NotBlank(message = "First name must not be blank")
    String firstName;

    @NotBlank(message = "Last name must not be blank")
    String lastName;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email should be valid")
    String email;

    @NotNull(message = "User type must not be null")
    UserType userType;

    @NotNull(message = "User details must not be null")
    UserDetails userDetails;

    // Optional validation: could also check if empty or not depending on use case
    List<ResultDtoForUsers> results;

    List<CourseDto> courses;
}