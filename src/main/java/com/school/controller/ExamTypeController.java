package com.school.controller;

import com.school.constants.ApiConstants;
import com.school.dtos.ExamTypeDto;
import com.school.entity.ExamType;
import com.school.mapper.ExamTypeMapper;
import com.school.service.ExamTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ApiConstants.EXAM_TYPE)
public class ExamTypeController {


    @Autowired
    private ExamTypeService examTypeService;

    //Add New ExamType
    @PostMapping(ApiConstants.ADD_EXAM_TYPE)
    public ResponseEntity<ExamTypeDto> addExamType(@RequestBody ExamTypeDto examTypeDto) {
        ExamType examType = ExamTypeMapper.toEntity(examTypeDto);

        examType = examTypeService.addExamType(examType);
        return ResponseEntity.ok(ExamTypeMapper.toDto(examType));
    }

    //Get All ExamType
    @GetMapping(ApiConstants.GET_ALL_EXAM_TYPES)
    public ResponseEntity<List<ExamTypeDto>> getAllExamType() {
        List<ExamType> examTypeList = examTypeService.getAllExamTypes();

        if (!examTypeList.isEmpty()) {
            List<ExamTypeDto> examTypeDtoList = examTypeList.stream()
                    .map(ExamTypeMapper::toDto)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(examTypeDtoList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    //Get ExamType with Id
    @GetMapping(ApiConstants.EXAM_TYPE_BY_ID)
    public ResponseEntity<ExamTypeDto> getExamTypeById(@PathVariable("examTypeId") Long examTypeId) {

        ExamType examType = examTypeService.getExamTypeById(examTypeId);
        ExamTypeDto examTypeDto = ExamTypeMapper.toDto(examType);
        return new ResponseEntity<>(examTypeDto, HttpStatus.OK);
    }


    //Delete ExamType with Id
    @DeleteMapping(ApiConstants.DELETE_EXAM_TYPE_BY_ID)
    public ResponseEntity<String> deleteExamType(@PathVariable Long examTypeId) {
        ExamType deletedExamType = examTypeService.deleteExamTypeById(examTypeId);

        if (deletedExamType != null) {
            String message = "ExamType deleted successfully with id: " + examTypeId;
            return ResponseEntity.ok(message);
        }

        String message = "ExamType with id: " + examTypeId + " not found";
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }


    //Updating the ExamType

    @PutMapping(ApiConstants.UPDATE_EXAM_TYPE)
    public ResponseEntity<ExamTypeDto> updateExamType(@RequestBody ExamTypeDto dto) {
        ExamType updated = examTypeService.updateExamType(ExamTypeMapper.toEntity(dto));
        return ResponseEntity.ok(ExamTypeMapper.toDto(updated));
    }


}
