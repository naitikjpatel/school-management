package com.school.dtos;

import lombok.*;
import lombok.experimental.FieldDefaults;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDetailsDto {

    Long userDetailId;

    @NotBlank(message = "Details must not be blank")
    @Size(min = 5, max = 255, message = "Details must be between 5 and 255 characters")
    String details;

    @NotBlank(message = "Address must not be blank")
    @Size(min = 10, max = 255, message = "Address must be between 10 and 255 characters")
    String address;

    @NotBlank(message = "Phone number must not be blank")
    @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 characters")
    String phone;
}
