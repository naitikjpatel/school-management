package com.school.mapper;

import com.school.dtos.SubjectDtoForCourse;
import com.school.entity.Subject;
import org.springframework.beans.BeanUtils;

public class SubjectDtoForCourseMapper {

    public static Subject toEntity(SubjectDtoForCourse subjectDtoForCourse) {
        Subject subject = new Subject();
        BeanUtils.copyProperties(subjectDtoForCourse, subject);
        return subject;
    }

//    public static SubjectDtoForCourse toDto(Subject subject) {
//        SubjectDtoForCourse subjectDtoForCourse = new SubjectDtoForCourse();
//        BeanUtils.copyProperties(subject, subjectDtoForCourse);
//        return subjectDtoForCourse;
//    }

    public static SubjectDtoForCourse toDto(Subject subject) {
        SubjectDtoForCourse dto = new SubjectDtoForCourse();
        dto.setSubjectId(subject.getSubjectId());
        dto.setSubjectName(subject.getSubjectName());
        // Do NOT copy subject.getCourse()
        return dto;
    }


}
