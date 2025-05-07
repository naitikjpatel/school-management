package com.school.mapper;

import com.school.dtos.CourseDtoForSubject;
import com.school.dtos.SubjectDto;
import com.school.entity.Subject;
import org.springframework.beans.BeanUtils;

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


//        if (subject.getCourse() != null) {
//            CourseDtoForSubject  courseDto = new CourseDtoForSubject();
//            BeanUtils.copyProperties(subject.getCourse(), courseDto);
//            subjectDto.setCourse(courseDto);
//        }
        return subjectDto;
    }



}
