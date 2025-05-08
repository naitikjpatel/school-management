package com.school.service;

import com.school.Exception.ResourceNotFoundException;
import com.school.entity.Exam;
import com.school.entity.ExamType;
import com.school.entity.Subject;
import com.school.mapper.ExamMapper;
import com.school.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {

    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private ExamTypeService examTypeService;
    @Autowired
    private SubjectService subjectService;

    //Add Exam Service
    public Exam addExam(Exam exam) {
        Long examTypeId = exam.getExamType().getExamTypeId(); // ensure it's sent in request
        ExamType examType = examTypeService.getExamTypeById(examTypeId);
        //examType Find
        exam.setExamType(examType);
        //subject find
        Subject subject = subjectService.getSubjectById(exam.getSubjects().getSubjectId());

        exam.setSubjects(subject);

        return examRepository.save(exam); // save only Exam, not ExamType
    }

    //Delete Exam Service
    public Exam deleteExamById(Long examId) {
        Exam exam = examRepository.findById(examId).orElseThrow(() -> new ResourceNotFoundException("Exam is not found with id" + examId));
        return exam;
    }

    //Get Exam By id
    public Exam getExamById(Long examId) {
//        Exam exam=examRepository.findById(examId).get();
//        if (exam!=null){
//            return exam;
//        }
//        return null;
        return examRepository.findById(examId).orElseThrow(() -> new ResourceNotFoundException("Exam is not found with id" + examId));

    }

    //Get All Exam
    public List<Exam> getAllExam() {
        return examRepository.findAll();
    }
}
