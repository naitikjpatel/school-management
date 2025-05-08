package com.school.dtos;

import com.school.entity.Exam;
import com.school.entity.Users;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResultDto {

    Long resultId;
    String status;
    Date resultDate;
    UsersDtoForResult users;
    ExamDtoForResult exam;

    @Override
    public String toString() {
        return "ResultDto{" +
                "resultId=" + resultId +
                ", status='" + status + '\'' +
                ", resultDate=" + resultDate +
//                ", users=" + users +
//                ", exam=" + exam +
                '}';
    }
}
