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

//in case if infinte problem occure then in examDto make a SubjectDto and ExamTypeDto.
//make a subjectdto and in that if there is dependent on the exam entity then remove it : if we not removing we getting the infinite response. so remove that dependancy