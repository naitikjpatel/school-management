package com.school.controller;

import com.school.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exam/")
public class ExamController {

    @Autowired
    private ExamService examService;

    @GetMapping("{examId}")

}
