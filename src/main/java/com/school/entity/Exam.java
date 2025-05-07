package com.school.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long examId;
    Date examDate;
    @ManyToOne
    @JoinColumn(name = "exam_type_id")
    ExamType examType;

    // Many Exams belong to one Subject
    @ManyToOne
    @JoinColumn(name = "subjects_id")
    Subject subjects;
}