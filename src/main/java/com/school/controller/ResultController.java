package com.school.controller;

import com.school.dtos.ResultDto;
import com.school.entity.Result;
import com.school.mapper.ResultMapper;
import com.school.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/result/")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @GetMapping("{resultId}")
    public ResponseEntity<?> getResult(@PathVariable("resultId") Long resultId) {
        Result result = resultService.getResultById(resultId);
        if (result == null) {
            return new ResponseEntity<>("Result is not found with id :" + resultId, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ResultMapper.toDto(result), HttpStatus.OK);
    }


    @GetMapping("getAllResult")
    public ResponseEntity<?> getAllResult() {
        List<Result> results = resultService.getAllResult();
        if (results == null) {
            return new ResponseEntity<>("Result is not found", HttpStatus.NO_CONTENT);
        }
        List<ResultDto> resultDtoList = results.stream().map(ResultMapper::toDto).collect(Collectors.toList());
        return new ResponseEntity<>(resultDtoList, HttpStatus.OK);
    }


    @PostMapping("addResult")
    public ResponseEntity<?> addResult(@RequestBody ResultDto resultDto) {
        System.out.println(resultDto.getUsers().getUserId());
        Result result = ResultMapper.toEntity(resultDto);
        System.out.println(result);


        result = resultService.addResult(result);
        return new ResponseEntity<>(ResultMapper.toDto(result), HttpStatus.OK);
    }
}
