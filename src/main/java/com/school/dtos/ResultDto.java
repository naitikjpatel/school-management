package com.school.dtos;

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
