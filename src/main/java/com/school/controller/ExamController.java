package com.school.controller;

import com.school.dtos.ExamDto;
import com.school.entity.Exam;
import com.school.mapper.ExamMapper;
import com.school.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/exam/")
public class ExamController {

    @Autowired
    private ExamService examService;

    @GetMapping("{examId}")
    public ResponseEntity<?> getExamById(@PathVariable("examId") Long examId) {
        Exam exam = examService.getExamById(examId);

        return new ResponseEntity<>(ExamMapper.toDto(exam), HttpStatus.OK);
    }

    @GetMapping("getAllExam")
    public ResponseEntity<List<ExamDto>> getAllExam() {
        List<Exam> exams = examService.getAllExam();
        if (exams == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<ExamDto>examDtoList=exams.stream().map(ExamMapper::toDto).collect(Collectors.toList());

        return new ResponseEntity<>(examDtoList, HttpStatus.OK);
    }

    @DeleteMapping("{examId}")
    public ResponseEntity<?> deleteExam(@PathVariable("examId") Long examId) {
        Exam exam = examService.deleteExamById(examId);
        if (exam == null) {
            return new ResponseEntity<>("Exam not found with id " + examId, HttpStatus.NOT_FOUND);
        }
        examService.deleteExamById(examId);
        return new ResponseEntity<>("Exam Deleted with Id : "+examId,HttpStatus.OK);
    }

    @PostMapping("addExam")
    public ResponseEntity<?> addExam(@RequestBody ExamDto examDto) {
        System.out.println(examDto.toString());
        Exam exam = ExamMapper.toEntity(examDto);
        exam=examService.addExam(exam);
        return new ResponseEntity<>(ExamMapper.toDto(exam), HttpStatus.OK);
    }


}
