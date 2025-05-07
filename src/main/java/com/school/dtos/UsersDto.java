package com.school.dtos;

import com.school.entity.Course;
import com.school.entity.Result;
import com.school.entity.UserDetails;
import com.school.entity.UserType;
import jakarta.persistence.*;
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
    String firstName;
    String lastName;
    String email;
    UserType userType;
    UserDetails userDetails;
    List<Result> results;
    List<CourseDto> courses;
}
