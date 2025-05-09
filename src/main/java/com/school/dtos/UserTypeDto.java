package com.school.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserTypeDto {


    Long userTypeId;

    @NotBlank(message = "User Type name must not be blank")
    @Size(min = 3, max = 50, message = "User Type name must be between 3 and 50 characters")
    String userTypes;
}