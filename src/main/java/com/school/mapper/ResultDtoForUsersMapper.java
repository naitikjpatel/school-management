package com.school.mapper;

import com.school.dtos.ResultDtoForUsers;
import com.school.entity.Result;
import org.springframework.beans.BeanUtils;

public class ResultDtoForUsersMapper {

    public  static Result toEntity(ResultDtoForUsers resultDtoForUsers) {
        Result result = new Result();
        BeanUtils.copyProperties(resultDtoForUsers, result);
        return result;
    }
    public static ResultDtoForUsers toDto(Result result) {
        ResultDtoForUsers resultDtoForUsers = new ResultDtoForUsers();
        BeanUtils.copyProperties(result, resultDtoForUsers);
        return resultDtoForUsers;
    }
}
