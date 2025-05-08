package com.school.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Table(name = "results")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long resultId;

    // Pass/Fail status
    String status; // "Pass" or "Fail"

    // Date of result or evaluation
    Date resultDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    Users users;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    Exam exam;

    @Override
    public String toString() {
        return "Result{" +
                "resultId=" + resultId +
                ", status='" + status + '\'' +
                ", resultDate=" + resultDate +
//                ", users=" + users +
//                ", exam=" + exam +
                '}';
    }
}
