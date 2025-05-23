package com.school.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "courses")

public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long courseId;

    String courseName;
    String courseDescription;

    //Course Table is Parent Table for the Student Table: for that we are putting the f.k in the Student Table (Child Table).
//    @JsonIgnore
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)

    List<Subject> subjects = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "courses" ,cascade = CascadeType.ALL)
    List<Users> users = new ArrayList<>();

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", courseDescription='" + courseDescription + '\'' +
                ", subjects=" + subjects +
                ", users=" + users +
                '}';
    }
}

