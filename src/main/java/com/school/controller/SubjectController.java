package com.school.controller;

import com.school.dtos.SubjectDto;
import com.school.entity.Subject;
import com.school.mapper.SubjectMapper;
import com.school.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/subject/")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    //Get All Subject
    @GetMapping("getAllSubject")
    public ResponseEntity<List<SubjectDto>> getAllSubject() {
        List<Subject> subjects = subjectService.getAllSubject();
        if (!subjects.isEmpty()) {
            List<SubjectDto> subjectDtoList = subjects.stream()
                    .map(SubjectMapper::toDto)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(subjectDtoList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Get Subject By SubjectId
    @GetMapping("{subjectId}")
    public ResponseEntity<SubjectDto> getSubjectById(@PathVariable("subjectId") Long subjectId) {
        Subject subject = subjectService.getSubjectById(subjectId);
        if (subject != null) {
            SubjectDto subjectDto = SubjectMapper.toDto(subject);
            return new ResponseEntity<>(subjectDto, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Add Subject
    @PostMapping("addSubject")
    public ResponseEntity<SubjectDto> addSubject(@RequestBody SubjectDto subjectDto) {
        Subject subject = SubjectMapper.toEntity(subjectDto);
        subject = subjectService.addSubject(subject, subjectDto.getCourse().getCourseId());
        return new ResponseEntity<>(SubjectMapper.toDto(subject), HttpStatus.CREATED);
    }


}
