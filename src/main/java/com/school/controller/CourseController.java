package com.school.controller;

import com.school.dtos.CourseDto;
import com.school.entity.Course;
import com.school.mapper.CourseMapper;
import com.school.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    //Add Course Url
    @PostMapping("addCourse")
    public ResponseEntity<CourseDto> addCourse(@RequestBody CourseDto courseDto) {
        //converting into the Course Object
        Course course = CourseMapper.toEntity(courseDto);
        course = courseService.addCourse(course);
        return new ResponseEntity<>(CourseMapper.toDto(course), HttpStatus.CREATED);
    }

    //Get All Course Url
    @GetMapping("getAllCourse")
    public ResponseEntity<List<CourseDto>> getAllCourse() {
        List<Course> courses = courseService.getAllCourse();
        if (!courses.isEmpty()) {
            List<CourseDto> courseDtoList = courses.stream()
                    .map(CourseMapper::toDto)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(courseDtoList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    //Get Course By Course Id]
    @GetMapping("{courseId}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable Long courseId) {
        Course course = courseService.getCourseById(courseId);
        if (course != null) {
            return new ResponseEntity<>(CourseMapper.toDto(course), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Course Delete By Id
    @DeleteMapping("{courseId}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long courseId) {
        Course course = courseService.deleteCourseById(courseId);
        if (course != null) {
            String message = "Course with id : " + courseId + " has been deleted";
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>("Course with id : " + courseId + " is not found", HttpStatus.NOT_FOUND);

    }

    //Update Course By The CourseId
    @PutMapping("updateCourse")
    public ResponseEntity<CourseDto> updateCourse(@RequestBody CourseDto courseDto) {
        Course course = CourseMapper.toEntity(courseDto);
        course = courseService.updateCourse(course);
        return new ResponseEntity<>(CourseMapper.toDto(course), HttpStatus.OK);
    }


}
