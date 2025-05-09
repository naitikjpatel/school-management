package com.school.controller;

import com.school.constants.ApiConstants;
import com.school.dtos.ExamTypeDto;
import com.school.entity.ExamType;
import com.school.mapper.ExamTypeMapper;
import com.school.service.ExamTypeService;
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
@RequestMapping(ApiConstants.EXAM_TYPE)
public class ExamTypeController {

    private static final Logger logger = LoggerFactory.getLogger(ExamTypeController.class);

    @Autowired
    private ExamTypeService examTypeService;

    // Add New ExamType
    @PostMapping(ApiConstants.ADD_EXAM_TYPE)
    public ResponseEntity<ExamTypeDto> addExamType(@Valid @RequestBody ExamTypeDto examTypeDto) {
        logger.info("Request received to add new exam type: {}", examTypeDto);

        ExamType examType = ExamTypeMapper.toEntity(examTypeDto);
        examType = examTypeService.addExamType(examType);

        logger.info("ExamType added successfully: {}", examType);
        return ResponseEntity.ok(ExamTypeMapper.toDto(examType));
    }

    // Get All ExamType
    @GetMapping(ApiConstants.GET_ALL_EXAM_TYPES)
    public ResponseEntity<List<ExamTypeDto>> getAllExamType() {
        logger.debug("Request received to fetch all exam types.");

        List<ExamType> examTypeList = examTypeService.getAllExamTypes();

        if (!examTypeList.isEmpty()) {
            List<ExamTypeDto> examTypeDtoList = examTypeList.stream()
                    .map(ExamTypeMapper::toDto)
                    .collect(Collectors.toList());

            logger.debug("Fetched {} exam types.", examTypeDtoList.size());
            return new ResponseEntity<>(examTypeDtoList, HttpStatus.OK);
        }

        logger.warn("No exam types found.");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get ExamType by Id
    @GetMapping(ApiConstants.EXAM_TYPE_BY_ID)
    public ResponseEntity<ExamTypeDto> getExamTypeById(@PathVariable("examTypeId") Long examTypeId) {
        logger.info("Request received to fetch exam type with ID: {}", examTypeId);

        ExamType examType = examTypeService.getExamTypeById(examTypeId);
        if (examType != null) {
            logger.info("ExamType found: {}", examType);
            ExamTypeDto examTypeDto = ExamTypeMapper.toDto(examType);
            return new ResponseEntity<>(examTypeDto, HttpStatus.OK);
        }

        logger.error("ExamType with ID {} not found.", examTypeId);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete ExamType by Id
    @DeleteMapping(ApiConstants.DELETE_EXAM_TYPE_BY_ID)
    public ResponseEntity<String> deleteExamType(@PathVariable Long examTypeId) {
        logger.info("Request received to delete exam type with ID: {}", examTypeId);

        ExamType deletedExamType = examTypeService.deleteExamTypeById(examTypeId);

        if (deletedExamType != null) {
            String message = "ExamType deleted successfully with id: " + examTypeId;
            logger.info(message);
            return ResponseEntity.ok(message);
        }

        String message = "ExamType with id: " + examTypeId + " not found.";
        logger.error(message);
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    // Update ExamType
    @PutMapping(ApiConstants.UPDATE_EXAM_TYPE)
    public ResponseEntity<ExamTypeDto> updateExamType(@RequestBody ExamTypeDto dto) {
        logger.info("Request received to update exam type: {}", dto);

        ExamType updated = examTypeService.updateExamType(ExamTypeMapper.toEntity(dto));
        logger.info("ExamType updated successfully: {}", updated);

        return ResponseEntity.ok(ExamTypeMapper.toDto(updated));
    }
}
