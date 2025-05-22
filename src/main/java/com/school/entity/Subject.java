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
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long subjectId;
    String subjectName;

    // Many Subjects belong to one Course
    @ManyToOne
//    @JsonIgnore
    @JoinColumn(name = "course_id")
    Course course;

    //Here Subject is a Parent Table For the Exam Table : for that we are putting f.k in the Exam Table (child Table)
    //One Subject Have Many Exam
    @OneToMany(mappedBy = "subjects", cascade = CascadeType.ALL)
    List<Exam> exam=new ArrayList<>();


}
