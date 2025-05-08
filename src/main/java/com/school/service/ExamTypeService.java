package com.school.service;

import com.school.Exception.ResourceNotFoundException;
import com.school.entity.ExamType;
import com.school.repository.ExamTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamTypeService {

    @Autowired
    private ExamTypeRepository examTypeRepository;

    //Get All ExamTypes Service
    public List<ExamType> getAllExamTypes() {
        return examTypeRepository.findAll();
    }

    //Get ExamTypeById Service
    public ExamType getExamTypeById(Long id) {
        return examTypeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ExamType is not found with id" + id));
    }

    //Delete ExamType By Id Service
    public ExamType deleteExamTypeById(Long id) {
        Optional<ExamType> examTypeOpt = examTypeRepository.findById(id);
        if (examTypeOpt.isPresent()) {
            examTypeRepository.delete(examTypeOpt.get());
            return examTypeOpt.get();
        }
        return null;

    }

    //Add ExamType Service
    public ExamType addExamType(ExamType examType) {
        String typeName = examType.getExamTypeName();

        // Check if ExamType already exists
        Optional<ExamType> existingTypeOpt = examTypeRepository.findByExamTypeName(typeName);

        if (existingTypeOpt.isPresent()) {
            return existingTypeOpt.get();
        }
        return examTypeRepository.save(examType);

    }


    //Update ExamType Service
    public ExamType updateExamType(ExamType examT) {
        ExamType examType = examTypeRepository.findById(examT.getExamTypeId()).orElseThrow(() -> new ResourceNotFoundException("ExamType not found with id: " + examT.getExamTypeId()));
        if (examT.getExamTypeName() != null) examType.setExamTypeName(examT.getExamTypeName());
        return examTypeRepository.save(examType);
    }

}