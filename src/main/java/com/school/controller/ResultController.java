package com.school.controller;

import com.school.constants.ApiConstants;
import com.school.dtos.ResultDto;
import com.school.dtos.ResultDtoForUser;
import com.school.entity.Result;
import com.school.mapper.ResultMapper;
import com.school.repository.ResultRepository;
import com.school.service.ResultService;
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
@RequestMapping(ApiConstants.RESULT)
@CrossOrigin(origins = "http://localhost:3000")
public class ResultController {

    private static final Logger logger = LoggerFactory.getLogger(ResultController.class);

    @Autowired
    private ResultService resultService;
    @Autowired
    private ResultRepository resultRepository;

    // Get Result by ID
    @GetMapping(ApiConstants.RESULT_BY_ID)
    public ResponseEntity<?> getResult(@PathVariable("resultId") Long resultId) {
        logger.info("Request received to fetch result with ID: {}", resultId);

        Result result = resultService.getResultById(resultId);
        if (result == null) {
            logger.error("Result with ID {} not found.", resultId);
            return new ResponseEntity<>("Result is not found with id :" + resultId, HttpStatus.NOT_FOUND);
        }

        logger.info("Result found: {}", result);
        return new ResponseEntity<>(ResultMapper.toDto(result), HttpStatus.OK);
    }

    // Get All Results
    @GetMapping(ApiConstants.GET_ALL_RESULTS)
    public ResponseEntity<?> getAllResult() {
        logger.info("Request received to fetch all results.");

        List<Result> results = resultService.getAllResult();
        if (results == null || results.isEmpty()) {
            logger.warn("No results found.");
            return new ResponseEntity<>("Result is not found", HttpStatus.NO_CONTENT);
        }

        List<ResultDto> resultDtoList = results.stream().map(ResultMapper::toDto).collect(Collectors.toList());
        logger.info("Fetched {} results.", resultDtoList.size());
        return new ResponseEntity<>(resultDtoList, HttpStatus.OK);
    }

    // Add New Result
    @PostMapping(ApiConstants.ADD_RESULT)
    public ResponseEntity<?> addResult(@Valid @RequestBody List<ResultDto> resultDtos) {
        for (ResultDto resultDto : resultDtos) {


            logger.info("Request received to add result for user: {}", resultDto.getUsers().getUserId());
//            List<Result>addedResult=resultRepository.findByUsers_UserIdAndExam_ExamId(resultDto.getUsers().getUserId(), resultDto.getExam().getExamId());
//            if (addedResult == null) {
                Result result = ResultMapper.toEntity(resultDto);
                logger.debug("Mapped Result entity: {}", result);

                result = resultService.addResult(result);

                logger.info("Result added successfully: {}", result);
//            }
        }
        return new ResponseEntity<>("Result Added Successfully", HttpStatus.OK);
    }

    // Delete Result by ID
    @DeleteMapping(ApiConstants.DELETE_RESULT_BY_ID)
    public ResponseEntity<?> deleteResultById(@PathVariable("resultId") Long resultId) {
        logger.info("Request received to delete result with ID: {}", resultId);

        Result result = resultService.deleteResultById(resultId);
        if (result == null) {
            logger.error("Result with ID {} not found for deletion.", resultId);
            return new ResponseEntity<>("Result is not found with id :" + resultId, HttpStatus.NOT_FOUND);
        }

        logger.info("Result with ID {} deleted successfully.", resultId);
        return new ResponseEntity<>("Result is deleted with id :" + resultId, HttpStatus.OK);
    }



    @GetMapping(ApiConstants.RESULT_BY_STUDENT_ID)
    public List<ResultDtoForUser> getStudentResults(@PathVariable Long userId) {//,@RequestParam(name = "subjectId") Long subjectId
        List<ResultDtoForUser> userResults=resultService.getResultsByUser(userId);
        return userResults;
    }

    @GetMapping(ApiConstants.RESULT_BY_EXAM_ID)
    public List<ResultDtoForUser> getTeacherResults(@PathVariable Long examId) {
        return resultService.getResultsByExam(examId);
    }
}
