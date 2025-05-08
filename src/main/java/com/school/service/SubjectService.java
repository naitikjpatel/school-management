package com.school.service;

import com.school.Exception.ResourceNotFoundException;
import com.school.entity.Course;
import com.school.entity.Subject;
import com.school.repository.CourseRepository;
import com.school.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseRepository courseRepository;

    //Get All Subject
    public List<Subject> getAllSubject() {
        return subjectRepository.findAll();
    }

    //Get Subject By I'd
    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id).orElse(null);

    }

    //Delete Subject By I'd
    public Subject deleteSubjectById(Long id) {
        Subject subject = subjectRepository.findById(id).orElse(null);
        if (subject != null) {
            subjectRepository.delete(subject);
            return subject;
        }
        return null;
    }

    //Get the All The Subject By CourseId Service
    public List<Subject> getSubjectByCourseId(Long courseId) {
        return subjectRepository.findByCourseCourseId(courseId);
    }

    //Add Subject Service
    public Subject addSubject(Subject subject, Long courseId) {
        Course course = courseService.getCourseById(courseId);
        //getting infinite : solution
        course.setSubjects(null);
        if (course != null) {
            subject.setCourse(course);
        }
        subjectRepository.save(subject);
        return subject;
    }

    //Update Subject By I'd Service
    public Subject updateSubject(Subject updatedSubject) {
        Subject existingSubject = subjectRepository.findById(updatedSubject.getSubjectId()).orElseThrow(() -> new ResourceNotFoundException("Subject not found with ID: " + updatedSubject.getSubjectId()));

        // Only update fields if not null
        if (updatedSubject.getSubjectName() != null) {
            existingSubject.setSubjectName(updatedSubject.getSubjectName());
        }

        // Optional: if you want to allow course change
        if (updatedSubject.getCourse() != null && updatedSubject.getCourse().getCourseId() != null) {
            Course newCourse = courseRepository.findById(updatedSubject.getCourse().getCourseId()).orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + updatedSubject.getCourse().getCourseId()));
            existingSubject.setCourse(newCourse);
        }

        return subjectRepository.save(existingSubject);
    }

}
