package com.school.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExamDto {

    Long examId;

    @NotNull(message = "Exam date must not be null")

    Date examDate;


    ExamTypeDtoForExam examType;


    SubjectDtoForCourse subjects;

    @Override
    public String toString() {
        return "ExamDto{" +
                "examId=" + examId +
                ", examDate=" + examDate +
                ", examType=" + examType +
                ", subjects=" + subjects +
                '}';
    }
}

//in case if infinite problem occur then in examDto make a SubjectDto and ExamTypeDto.
//make a subjectdto and in that if there is dependent on the exam entity then remove it : if we're not removing we're getting the infinite response. so remove that dependency