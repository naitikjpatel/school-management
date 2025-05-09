package com.school.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExamTypeDto {

    Long examTypeId;

    @NotBlank(message = "Exam type name must not be blank")
    @Size(min = 3, max = 100, message = "Exam type name must be between 3 and 100 characters")
    String examTypeName;


    List<ExamDtoForExamType> exams;
}
