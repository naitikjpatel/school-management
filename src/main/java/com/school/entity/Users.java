package com.school.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userId;
    String firstName;
    String lastName;
    String email;

    //one-to-one relation with the user details entity
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_detail_id")
    UserDetails userDetails;

    //many-to-one relation with usertype entity
    @ManyToOne
    @JoinColumn(name = "user_type_id")
    UserType userType;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    List<Result> results = new ArrayList<>();

    //many-to-many relationship with the course entity
    @ManyToMany
    @ToString.Exclude
    @JoinTable(
            name = "user_courses",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    List<Course> courses = new ArrayList<>();

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", userDetails=" + userDetails +
                ", userType=" + userType +
                ", results=" + results +
                '}';
    }
}
