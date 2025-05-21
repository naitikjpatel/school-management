package com.school.service;

import com.school.Exception.ResourceNotFoundException;
import com.school.dtos.ResultDtoForUser;
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
    private UserService userService;

    @Autowired
    private ExamService examService;


    //Add Result Service
    public Result addResult(Result result) {
        Users user = userService.getUserById(result.getUsers().getUserId());
        Exam exam = examService.getExamById(result.getExam().getExamId());
        result.setUsers(user);
        result.setExam(exam);
        resultRepository.save(result);
        return result;
    }

    //Get Result BY I'd Service
    public Result getResultById(Long id) {
        return resultRepository.findById(id).orElse(null);

    }

    //Get All Result Service
    public List<Result> getAllResult() {
        return resultRepository.findAll();
    }

    //Update Result Service
    public Result updateResult(Result updatedResult) {
        Result existingResult = resultRepository.findById(updatedResult.getResultId())
                .orElseThrow(() -> new ResourceNotFoundException("Result not found with ID: " + updatedResult.getResultId()));

        // Update fields
        existingResult.setStatus(updatedResult.getStatus());
        existingResult.setResultDate(updatedResult.getResultDate());

        if (updatedResult.getUsers() != null) {
            Users user = userService.getUserById(updatedResult.getUsers().getUserId());
            existingResult.setUsers(user);
        }
        if (updatedResult.getExam() != null) {
            Exam exam = examService.getExamById(updatedResult.getExam().getExamId());
            existingResult.setExam(exam);
        }
        return resultRepository.save(existingResult);
    }


    public Result deleteResultById(Long id) {
        Result existingResult = resultRepository.findById(id).orElse(null);
        if (existingResult != null) {
            resultRepository.delete(existingResult);
            return existingResult;
        }
        return null;
    }


    public List<ResultDtoForUser> getResultsByUser(Long userId) {
        return resultRepository.findResultsByUserId(userId);
    }

    public List<ResultDtoForUser> getResultsByExam(Long examId) {
        return resultRepository.findResultsByExamId(examId);
    }

}
