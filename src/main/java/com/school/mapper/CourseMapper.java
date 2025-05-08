package com.school.mapper;

import com.school.dtos.CourseDto;
import com.school.dtos.SubjectDtoForCourse;
import com.school.entity.Course;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class CourseMapper {

    public static Course toEntity(CourseDto courseDto) {
        Course course = new Course();
        BeanUtils.copyProperties(courseDto, course);
        return course;
    }
    public static CourseDto toDto(Course course) {
        CourseDto courseDto = new CourseDto();
        BeanUtils.copyProperties(course, courseDto);

        List<SubjectDtoForCourse> subjectDtos = course.getSubjects().stream()
                .map(SubjectDtoForCourseMapper::toDto)
                .collect(Collectors.toList());

        courseDto.setSubjects(subjectDtos);
        return courseDto;
    }


}
