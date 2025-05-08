package com.school.mapper;

import com.school.dtos.CourseDtoForSubject;
import com.school.entity.Course;
import org.springframework.beans.BeanUtils;

public class CourseDtoForSubjectMapper {

    public static Course toEntity(CourseDtoForSubject courseDtoForSubject) {
        Course course = new Course();
        BeanUtils.copyProperties(courseDtoForSubject, course);
        return course;
    }

    public static CourseDtoForSubject toDto(Course course) {
        CourseDtoForSubject courseDtoForSubject = new CourseDtoForSubject();
        BeanUtils.copyProperties(course, courseDtoForSubject);
        return courseDtoForSubject;
    }
}
