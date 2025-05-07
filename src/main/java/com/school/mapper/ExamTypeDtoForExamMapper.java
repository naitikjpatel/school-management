package com.school.mapper;

import com.school.dtos.ExamTypeDto;
import com.school.dtos.ExamTypeDtoForExam;
import com.school.entity.ExamType;
import org.springframework.beans.BeanUtils;

public class ExamTypeDtoForExamMapper {
    public static ExamType toEntity(ExamTypeDtoForExam examTypeDtoForExam) {
        ExamType examType = new ExamType();
        BeanUtils.copyProperties(examTypeDtoForExam, examType);
        return examType;
    }
    public static ExamTypeDtoForExam toDto(ExamType examType) {
        ExamTypeDtoForExam examTypeDtoForExam = new ExamTypeDtoForExam();
        BeanUtils.copyProperties(examType, examTypeDtoForExam);
        return examTypeDtoForExam;
    }
}
