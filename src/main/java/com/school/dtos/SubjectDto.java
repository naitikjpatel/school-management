package com.school.dtos;

import com.school.entity.Course;
import com.school.entity.Exam;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
