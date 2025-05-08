package com.school.mapper;

import com.school.dtos.ResultDto;
import com.school.entity.Result;
import org.springframework.beans.BeanUtils;

public class ResultMapper {

    public static Result toEntity(ResultDto resultDto) {
        Result result = new Result();
        BeanUtils.copyProperties(resultDto, result);
        System.out.println(resultDto.getUsers().getUserId());

        result.setUsers(UsersDtoForResultMapper.toEntity(resultDto.getUsers()));
        System.out.println(result.getUsers().getUserId());
        result.setExam(ExamDtoForResultMapper.toEntity(resultDto.getExam()));
        return result;
    }
    public static ResultDto toDto(Result result) {
        ResultDto resultDto = new ResultDto();
        BeanUtils.copyProperties(result, resultDto);
        resultDto.setUsers(UsersDtoForResultMapper.toDto(result.getUsers()));
        resultDto.setExam(ExamDtoForResultMapper.toDto(result.getExam()));
        return resultDto;
    }
}
