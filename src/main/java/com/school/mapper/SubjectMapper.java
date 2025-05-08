package com.school.mapper;

import com.school.dtos.CourseDtoForSubject;
import com.school.dtos.ExamDto;
import com.school.dtos.SubjectDto;
import com.school.dtos.SubjectDtoForCourse;
import com.school.entity.Subject;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class SubjectMapper {

    public static Subject toEntity(SubjectDto subjectDto) {
        Subject subject = new Subject();
        BeanUtils.copyProperties(subjectDto, subject);
        return subject;
    }

    public static SubjectDto toDto(Subject subject) {
        SubjectDto subjectDto = new SubjectDto();
        BeanUtils.copyProperties(subject, subjectDto);
        subjectDto.setCourse(CourseDtoForSubjectMapper.toDto(subject.getCourse()));

        List<ExamDto> examDtoList = subject.getExam().stream()
                .map(ExamMapper::toDto)
                .collect(Collectors.toList());

        //New Line Added At 7 may
        examDtoList.forEach(examDto -> examDto.setSubjects(null));
        subjectDto.setExam(examDtoList);


//        if (subject.getCourse() != null) {
//            CourseDtoForSubject  courseDto = new CourseDtoForSubject();
//            BeanUtils.copyProperties(subject.getCourse(), courseDto);
//            subjectDto.setCourse(courseDto);
//        }
        return subjectDto;
    }


}
