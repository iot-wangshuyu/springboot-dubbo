package com.shuyu.service.mapper;

import com.shuyu.api.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.shuyu.api.dto.UserDTO;
@Mapper
public interface UserMapper {
    List<User> listUser();
}
