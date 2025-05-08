package com.school.mapper;

import com.school.dtos.ExamDto;
import com.school.dtos.ExamDtoForExamType;
import com.school.dtos.ExamTypeDto;
import com.school.entity.Exam;
import com.school.entity.ExamType;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;


public class ExamTypeMapper {

    public static ExamType toEntity(ExamTypeDto dto) {
        ExamType examType = new ExamType();
        BeanUtils.copyProperties(dto, examType);

        if (dto.getExams() != null) {
            List<Exam> exams = dto.getExams()
                    .stream()
                    .map(examDto -> {
                        Exam exam = new Exam();
                        BeanUtils.copyProperties(examDto, exam);
                        // Subjects are not set, since they were removed
                        return exam;
                    })
                    .collect(Collectors.toList());
            examType.setExams(exams);
        }

        return examType;
    }

    public static ExamTypeDto toDto(ExamType examType) {
        ExamTypeDto dto = new ExamTypeDto();
        BeanUtils.copyProperties(examType, dto);

        if (examType.getExams() != null) {
            List<ExamDtoForExamType> examDtos = examType.getExams()
                    .stream()
                    .map(exam -> {
                        ExamDtoForExamType e = new ExamDtoForExamType();
                        BeanUtils.copyProperties(exam, e);
                        // Removed: e.setSubjects(SubjectDtoForCourseMapper.toDto(exam.getSubjects()));
                        return e;
                    })
                    .collect(Collectors.toList());
            dto.setExams(examDtos);
        }

        return dto;
//        ExamTypeDto examTypeDto = new ExamTypeDto();
//        BeanUtils.copyProperties(examType, examTypeDto);
//        return examTypeDto;
    }
}
