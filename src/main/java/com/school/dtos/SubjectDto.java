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
public class SubjectDto {

    Long subjectId;

    @NotBlank(message = "Subject name must not be blank")
    @Size(min = 3, max = 100, message = "Subject name must be between 3 and 100 characters")
    String subjectName;

    Double subjectScore;
    CourseDtoForSubject course;
    List<ExamDto> exam;
}
