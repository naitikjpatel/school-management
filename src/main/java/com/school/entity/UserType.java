package com.school.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userTypeId;

    String userTypes;

//    @OneToMany(mappedBy = "userType",cascade = CascadeType.ALL)
//    List<Users> users;
}
