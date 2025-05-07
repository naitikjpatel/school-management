package com.school.dtos;

import com.school.entity.ExamType;
import com.school.entity.Subject;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExamDto {
    Long examId;
    Date examDate;
    ExamType examType;
    Subject subjects;
}
