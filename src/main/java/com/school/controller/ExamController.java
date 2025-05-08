package com.school.controller;

import com.school.constants.ApiConstants;
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
@RequestMapping(ApiConstants.EXAM)
public class ExamController {

    @Autowired
    private ExamService examService;

    @GetMapping(ApiConstants.EXAM_BY_ID)
    public ResponseEntity<?> getExamById(@PathVariable("examId") Long examId) {
        Exam exam = examService.getExamById(examId);

        return new ResponseEntity<>(ExamMapper.toDto(exam), HttpStatus.OK);
    }

    @GetMapping(ApiConstants.GET_ALL_EXAMS)
    public ResponseEntity<List<ExamDto>> getAllExam() {
        List<Exam> exams = examService.getAllExam();
        if (exams == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<ExamDto> examDtoList = exams.stream().map(ExamMapper::toDto).collect(Collectors.toList());

        return new ResponseEntity<>(examDtoList, HttpStatus.OK);
    }

    @DeleteMapping(ApiConstants.DELETE_EXAM_BY_ID)
    public ResponseEntity<?> deleteExam(@PathVariable("examId") Long examId) {
        Exam exam = examService.deleteExamById(examId);
        if (exam == null) {
            return new ResponseEntity<>("Exam not found with id " + examId, HttpStatus.NOT_FOUND);
        }
        examService.deleteExamById(examId);
        return new ResponseEntity<>("Exam Deleted with Id : " + examId, HttpStatus.OK);
    }

    @PostMapping(ApiConstants.ADD_EXAM)
    public ResponseEntity<?> addExam(@RequestBody ExamDto examDto) {
        System.out.println(examDto.toString());
        Exam exam = ExamMapper.toEntity(examDto);
        exam = examService.addExam(exam);
        return new ResponseEntity<>(ExamMapper.toDto(exam), HttpStatus.OK);
    }


}
