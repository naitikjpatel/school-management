package com.school.controller;

import com.school.constants.ApiConstants;
import com.school.dtos.CourseDto;
import com.school.entity.Course;
import com.school.mapper.CourseMapper;
import com.school.service.CourseService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ApiConstants.COURSE)
public class CourseController {

    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private CourseService courseService;

    @PostMapping(ApiConstants.ADD_COURSE)
    public ResponseEntity<CourseDto> addCourse(@Valid @RequestBody CourseDto courseDto) {
        logger.info("Request received to add a new course: {}", courseDto);

        // Converting into the Course Object
        Course course = CourseMapper.toEntity(courseDto);
        course = courseService.addCourse(course);

        logger.info("Course added successfully: {}", course);
        return new ResponseEntity<>(CourseMapper.toDto(course), HttpStatus.CREATED);
    }

    @GetMapping(ApiConstants.GET_ALL_COURSES)
    public ResponseEntity<List<CourseDto>> getAllCourse() {
        logger.debug("Request received to fetch all courses.");

        List<Course> courses = courseService.getAllCourse();
        if (!courses.isEmpty()) {
            List<CourseDto> courseDtoList = courses.stream()
                    .map(CourseMapper::toDto)
                    .collect(Collectors.toList());

            logger.debug("Courses fetched successfully: {}", courseDtoList.size());
            return new ResponseEntity<>(courseDtoList, HttpStatus.OK);
        }

        logger.warn("No courses found.");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(ApiConstants.COURSE_BY_ID)
    public ResponseEntity<CourseDto> getCourseById(@PathVariable Long courseId) {
        logger.info("Request received to fetch course with ID: {}", courseId);

        Course course = courseService.getCourseById(courseId);
        if (course != null) {
            logger.info("Course found: {}", course);
            return new ResponseEntity<>(CourseMapper.toDto(course), HttpStatus.OK);
        }

        logger.error("Course with ID {} not found.", courseId);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(ApiConstants.DELETE_COURSE_BY_ID)
    public ResponseEntity<String> deleteCourse(@PathVariable Long courseId) {
        logger.info("Request received to delete course with ID: {}", courseId);

        Course course = courseService.deleteCourseById(courseId);
        if (course != null) {
            String message = "Course with id : " + courseId + " has been deleted";
            logger.info(message);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }

        logger.error("Course with ID {} not found for deletion.", courseId);
        return new ResponseEntity<>("Course with id : " + courseId + " is not found", HttpStatus.NOT_FOUND);
    }


    @PutMapping(ApiConstants.UPDATE_COURSE)
    public ResponseEntity<CourseDto> updateCourse(@RequestBody CourseDto courseDto) {
        logger.info("Request received to update course: {}", courseDto);

        Course course = CourseMapper.toEntity(courseDto);
        course = courseService.updateCourse(course);

        logger.info("Course updated successfully: {}", course);
        return new ResponseEntity<>(CourseMapper.toDto(course), HttpStatus.OK);
    }
}
