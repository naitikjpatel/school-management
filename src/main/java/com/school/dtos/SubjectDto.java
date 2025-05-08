package com.school.dtos;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubjectDto {
    Long subjectId;
    String subjectName;
    CourseDtoForSubject course;
    List<ExamDto> exam;

}
