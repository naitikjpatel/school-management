package com.school.mapper;

import com.school.dtos.ExamDtoForResult;
import com.school.entity.Exam;
import org.springframework.beans.BeanUtils;

public class ExamDtoForResultMapper {

    public static Exam toEntity(ExamDtoForResult examDtoForResult) {
        Exam exam = new Exam();
        BeanUtils.copyProperties(examDtoForResult, exam);
        return exam;
    }
    public static ExamDtoForResult toDto(Exam exam) {
        ExamDtoForResult examDtoForResult = new ExamDtoForResult();
        BeanUtils.copyProperties(exam, examDtoForResult);
        return examDtoForResult;
    }
}
