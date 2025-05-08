package com.school.dtos;

import com.school.entity.UserDetails;
import com.school.entity.UserType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UsersDtoForResult {
    Long userId;
    String firstName;
    String lastName;
    String email;
    UserType userType;
    UserDetails userDetails;
}
