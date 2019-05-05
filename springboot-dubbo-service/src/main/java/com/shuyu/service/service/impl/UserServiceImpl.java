package com.shuyu.service.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.shuyu.api.dto.UserDTO;
import com.shuyu.api.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * @package: com.shuyu.service.service.impl
 * @className: UserServiceImpl
 * @description:
 * @author: Shuyu.Wang
 * @date: 2019-05-05 16:27
 * @since: 0.1
 **/
@Service
public class UserServiceImpl implements UserService {

	@Override
	public List<UserDTO> listUser() {
		List<UserDTO> userDTOList=new ArrayList<>();
		userDTOList.add(UserDTO.builder().userCode("123").userName("测试").build());
		return userDTOList;
	}
}
