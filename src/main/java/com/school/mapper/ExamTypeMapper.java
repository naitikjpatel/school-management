package com.school.mapper;

import com.school.dtos.ExamTypeDto;
import com.school.entity.ExamType;
import org.springframework.beans.BeanUtils;


public class ExamTypeMapper {

    public static ExamType toEntity(ExamTypeDto examTypeDto) {
        ExamType examType = new ExamType();
        BeanUtils.copyProperties(examTypeDto, examType);
        return examType;
    }
    public static ExamTypeDto toDto(ExamType examType) {
        ExamTypeDto examTypeDto = new ExamTypeDto();
        BeanUtils.copyProperties(examType, examTypeDto);
        return examTypeDto;
    }
}
