package com.school.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "examtypes")
public class ExamType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long examTypeId;
    String examTypeName;

    @OneToMany(mappedBy = "examType", cascade = CascadeType.ALL)

    List<Exam> exams=new ArrayList<>() ;



}
