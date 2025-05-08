package com.school.service;

import com.school.Exception.ResourceNotFoundException;
import com.school.entity.Course;
import com.school.entity.Subject;
import com.school.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

//    addCourse, getCourses, updateCourse, deleteCourse.

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private SubjectService subjectService;

    //Get All Courses Service
    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    //Get Course By Id Service
    public Course getCourseById(Long id) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course != null) {
            List<Subject> subjectList = subjectService.getSubjectByCourseId(id);
            course.setSubjects(subjectList);
        }

        return course;
    }

    //Course Add Service
    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    //Delete Course By Id service
    public Course deleteCourseById(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()) {
            courseRepository.deleteById(id);
            return course.get();
        }
        return null;
    }

    //Update Course By Id Service
    public Course updateCourse(Course course) {
        Course existingCourse = courseRepository.findById(course.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + course.getCourseId()));

        // Only update if value is not null
        if (course.getCourseName() != null) {
            existingCourse.setCourseName(course.getCourseName());
        }

        if (course.getCourseDescription() != null) {
            existingCourse.setCourseDescription(course.getCourseDescription());
        }

        return courseRepository.save(existingCourse);
    }

}
