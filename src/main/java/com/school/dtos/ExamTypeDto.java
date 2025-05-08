package com.school.dtos;

import com.school.entity.Exam;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
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
