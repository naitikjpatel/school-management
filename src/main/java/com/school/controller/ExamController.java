package com.school.controller;

import com.school.constants.ApiConstants;
import com.school.dtos.ExamDto;
import com.school.entity.Exam;
import com.school.mapper.ExamMapper;
import com.school.service.ExamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ApiConstants.EXAM)
public class ExamController {

    private static final Logger logger = LoggerFactory.getLogger(ExamController.class);

    @Autowired
    private ExamService examService;

    @GetMapping(ApiConstants.EXAM_BY_ID)
    public ResponseEntity<?> getExamById(@PathVariable("examId") Long examId) {
        logger.info("Request received to fetch exam with ID: {}", examId);

        Exam exam = examService.getExamById(examId);
        if (exam != null) {
            logger.info("Exam found: {}", exam);
            return new ResponseEntity<>(ExamMapper.toDto(exam), HttpStatus.OK);
        }

        logger.error("Exam with ID {} not found.", examId);
        return new ResponseEntity<>("Exam not found with id " + examId, HttpStatus.NOT_FOUND);
    }

    @GetMapping(ApiConstants.GET_ALL_EXAMS)
    public ResponseEntity<List<ExamDto>> getAllExam() {
        logger.debug("Request received to fetch all exams.");

        List<Exam> exams = examService.getAllExam();
        if (exams == null || exams.isEmpty()) {
            logger.warn("No exams found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<ExamDto> examDtoList = exams.stream()
                .map(ExamMapper::toDto)
                .collect(Collectors.toList());

        logger.debug("Exams fetched successfully: {}", examDtoList.size());
        return new ResponseEntity<>(examDtoList, HttpStatus.OK);
    }

    @DeleteMapping(ApiConstants.DELETE_EXAM_BY_ID)
    public ResponseEntity<?> deleteExam(@PathVariable("examId") Long examId) {
        logger.info("Request received to delete exam with ID: {}", examId);

        Exam exam = examService.deleteExamById(examId);
        if (exam == null) {
            logger.error("Exam with ID {} not found for deletion.", examId);
            return new ResponseEntity<>("Exam not found with id " + examId, HttpStatus.NOT_FOUND);
        }

        logger.info("Exam deleted successfully with ID: {}", examId);
        return new ResponseEntity<>("Exam Deleted with Id : " + examId, HttpStatus.OK);
    }

    @PostMapping(ApiConstants.ADD_EXAM)
    public ResponseEntity<?> addExam(@Valid @RequestBody ExamDto examDto) {
        logger.info("Request received to add a new exam: {}", examDto);

        Exam exam = ExamMapper.toEntity(examDto);
        exam = examService.addExam(exam);

        logger.info("Exam added successfully: {}", exam);
        return new ResponseEntity<>(ExamMapper.toDto(exam), HttpStatus.CREATED);
    }
}
