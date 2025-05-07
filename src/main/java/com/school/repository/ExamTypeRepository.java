package com.school.repository;

import com.school.entity.ExamType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExamTypeRepository extends JpaRepository<ExamType, Long> {

    Optional<ExamType> findByExamTypeName(String typeName);


}
