package com.school.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResultDto {

    Long resultId;

    @NotBlank(message = "Status must not be blank")
    @Size(min = 3, max = 20, message = "Status must be between 3 and 20 characters")
    String status;

    @NotNull(message = "Result date must not be null")
    @PastOrPresent(message = "Result date must be in the past or present")
    Date resultDate;

    @NotNull(message = "User information must not be null")
    UsersDtoForResult users;

    @NotNull(message = "Exam information must not be null")
    ExamDtoForResult exam;

    @Override
    public String toString() {
        return "ResultDto{" +
                "resultId=" + resultId +
                ", status='" + status + '\'' +
                ", resultDate=" + resultDate +
                '}';
    }
}
