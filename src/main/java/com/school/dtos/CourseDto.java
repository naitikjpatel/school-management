package com.school.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseDto {

    Long courseId;

    @NotBlank(message = "Course name must not be blank")
//    @Size(min = 3, max = 100, message = "Course name must be between 3 and 100 characters")
    String courseName;

    @NotBlank(message = "Course description must not be blank")
//    @Size(min = 10, max = 500, message = "Course description must be between 10 and 500 characters")
    String courseDescription;


    List<SubjectDtoForCourse> subjects;
}

//    List<Users>users;

