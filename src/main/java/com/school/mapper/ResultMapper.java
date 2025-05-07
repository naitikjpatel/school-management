package com.school.mapper;

import com.school.dtos.ResultDto;
import com.school.entity.Result;
import org.springframework.beans.BeanUtils;

public class ResultMapper {

    public static Result toEntity(ResultDto resultDto) {
        Result result = new Result();
        BeanUtils.copyProperties(resultDto, result);

        return result;
    }
    public static ResultDto toDto(Result result) {
        ResultDto resultDto = new ResultDto();
        BeanUtils.copyProperties(result, resultDto);
//        resultDto.setUsers(UsersDtoResultMapper.toDto(result.getUsers()));
        return resultDto;
    }
}
