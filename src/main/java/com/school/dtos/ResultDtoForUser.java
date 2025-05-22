package com.school.dtos;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ResultDtoForUser {
     Long resultId;
     String status;
     Date resultDate;
     Long userId;
     String userName;
     Long examId;
     String examName;
     Long subjectId;
     String subjectName;


}
