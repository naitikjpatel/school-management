package com.school.service;

import com.school.Exception.ResourceNotFoundException;
import com.school.entity.Exam;
import com.school.entity.Result;
import com.school.entity.Users;
import com.school.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private  UserService userService;

    @Autowired
    private ExamService examService;


    //Add Result Service
    public Result addResult(Result result){
        Users user=userService.getUserById(result.getUsers().getUserId());
        Exam exam=examService.getExamById(result.getExam().getExamId());
        result.setUsers(user);
        result.setExam(exam);
        resultRepository.save(result);
        return result;
    }

    public Result getResultById(Long id){
        Result result=resultRepository.findById(id).orElse(null);
        return result;
    }
    public List<Result> getAllResult(){
        return resultRepository.findAll();
    }
    public Result updateResult( Result updatedResult) {
        Result existingResult = resultRepository.findById(updatedResult.getResultId())
                .orElseThrow(() -> new ResourceNotFoundException("Result not found with ID: " + updatedResult.getResultId()));

        // Update fields
        existingResult.setStatus(updatedResult.getStatus());
        existingResult.setResultDate(updatedResult.getResultDate());

        if (updatedResult.getUsers() != null) {
            Users user =userService.getUserById(updatedResult.getUsers().getUserId());
                    existingResult.setUsers(user);
        }

        if (updatedResult.getExam() != null) {
            Exam exam = examService.getExamById(updatedResult.getExam().getExamId());
                  existingResult.setExam(exam);
        }

        return resultRepository.save(existingResult);
    }





}
