package com.school.dtos;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExamTypeDto {
    Long examTypeId;
    String examTypeName;
    List<ExamDtoForExamType> exams;
}
