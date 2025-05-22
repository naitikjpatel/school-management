package com.school.repository;

import com.school.dtos.ResultDtoForUser;
import com.school.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {


        @Query("SELECT new com.school.dtos.ResultDtoForUser(r.resultId, r.status, r.resultDate, u.userId, u.firstName, e.examId, e.examType.examTypeName,s.subjectId,s.subjectName) " +
                "FROM Result r " +
                "JOIN r.users u " +
                "JOIN r.exam e " +
                "JOIN e.subjects s " +
                "WHERE u.userId = :userId")
        List<ResultDtoForUser> findResultsByUserId(@Param("userId") Long userId);

        @Query("SELECT new com.school.dtos.ResultDtoForUser(r.resultId, r.status, r.resultDate, u.userId, u.firstName, e.examId, e.examType.examTypeName,s.subjectId,s.subjectName) " +
                "FROM Result r " +
                "JOIN r.users u " +
                "JOIN r.exam e " +
                "JOIN e.subjects s " +
                "WHERE e.examId = :examId")
        List<ResultDtoForUser> findResultsByExamId(@Param("examId") Long examId);


                List<Result> findByUsers_UserIdAndExam_ExamId(Long userId, Long examId);




}
