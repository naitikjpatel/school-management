package com.school.controller;

import com.school.constants.ApiConstants;
import com.school.dtos.SubjectDto;
import com.school.entity.Subject;
import com.school.mapper.SubjectMapper;
import com.school.service.SubjectService;
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
@RequestMapping(ApiConstants.SUBJECT)
@CrossOrigin(origins = "http://localhost:3000")
public class SubjectController {

    private static final Logger logger = LoggerFactory.getLogger(SubjectController.class);

    @Autowired
    private SubjectService subjectService;

    // Get All Subjects
    @GetMapping(ApiConstants.GET_ALL_SUBJECTS)
    public ResponseEntity<List<SubjectDto>> getAllSubject() {
        logger.info("Request received to fetch all subjects.");

        List<Subject> subjects = subjectService.getAllSubject();
        if (!subjects.isEmpty()) {
            List<SubjectDto> subjectDtoList = subjects.stream()
                    .map(SubjectMapper::toDto)
                    .collect(Collectors.toList());
            logger.info("Fetched {} subjects.", subjectDtoList.size());
            return new ResponseEntity<>(subjectDtoList, HttpStatus.OK);
        }

        logger.warn("No subjects found.");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get Subject By SubjectId
    @GetMapping(ApiConstants.SUBJECT_BY_ID)
    public ResponseEntity<SubjectDto> getSubjectById(@PathVariable("subjectId") Long subjectId) {
        logger.info("Request received to fetch subject with ID: {}", subjectId);

        Subject subject = subjectService.getSubjectById(subjectId);
        if (subject != null) {
            logger.info("Subject found: {}", subject);
            SubjectDto subjectDto = SubjectMapper.toDto(subject);
            return new ResponseEntity<>(subjectDto, HttpStatus.OK);
        }

        logger.error("Subject with ID {} not found.", subjectId);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Add New Subject
    @PostMapping(ApiConstants.ADD_SUBJECT)
    public ResponseEntity<SubjectDto> addSubject(@Valid @RequestBody SubjectDto subjectDto) {
        logger.info("Request received to add new subject: {}", subjectDto.getSubjectName());

        Subject subject = SubjectMapper.toEntity(subjectDto);
        logger.debug("Mapped Subject entity: {}", subject);

        subject = subjectService.addSubject(subject, subjectDto.getCourse().getCourseId());
        logger.info("Subject added successfully: {}", subject);

        return new ResponseEntity<>(SubjectMapper.toDto(subject), HttpStatus.CREATED);
    }
}
