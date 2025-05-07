package com.school.mapper;

import com.school.dtos.ExamDto;
import com.school.entity.Exam;
import org.springframework.beans.BeanUtils;

public class ExamMapper {

    public  static Exam toEntity(ExamDto examDto){
        Exam exam = new Exam();
        BeanUtils.copyProperties(examDto,exam);
        return exam;
    }


    public  static ExamDto toDto(Exam exam){
        ExamDto examDto = new ExamDto();
        BeanUtils.copyProperties(exam,examDto);
        return examDto;
    }
}
