package com.school.dtos;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseDto {
    Long courseId;
    String courseName;
    String courseDescription;
    List<SubjectDtoForCourse> subjects;
//    List<Users>users;
}
