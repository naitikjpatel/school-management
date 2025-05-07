package com.school.mapper;

import com.school.dtos.ExamDto;
import com.school.dtos.ExamTypeDtoForExam;
import com.school.dtos.SubjectDtoForCourse;
import com.school.entity.Exam;
import org.springframework.beans.BeanUtils;

public class ExamMapper {

    public  static Exam toEntity(ExamDto examDto){
        Exam exam = new Exam();
        BeanUtils.copyProperties(examDto,exam);
        exam.setExamType(ExamTypeDtoForExamMapper.toEntity(examDto.getExamType()));
        exam.setSubjects(SubjectDtoForCourseMapper.toEntity(examDto.getSubjects()));
        return exam;
    }


    public  static ExamDto toDto(Exam exam){
        ExamDto examDto = new ExamDto();
        BeanUtils.copyProperties(exam,examDto);
        examDto.setExamType(ExamTypeDtoForExamMapper.toDto(exam.getExamType()));
        examDto.setSubjects(SubjectDtoForCourseMapper.toDto(exam.getSubjects()));
        return examDto;
    }
}
