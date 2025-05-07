package com.school.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "results")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long resultId;
    Double percentage;
    Double percentile;


    @ManyToOne
    @JoinColumn(name = "user_id")
    Users users;
}
