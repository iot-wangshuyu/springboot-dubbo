package com.shuyu.service.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.shuyu.api.dto.UserDTO;
import com.shuyu.api.po.User;
import com.shuyu.api.service.UserService;
import com.shuyu.service.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @package: com.shuyu.service.service.impl
 * @className: UserServiceImpl
 * @description:
 * @author: Shuyu.Wang
 * @date: 2019-05-05 16:27
 * @since: 0.1
 **/
@Service(version = "1.0.0")
@org.springframework.stereotype.Service
@Slf4j
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public List<UserDTO> listUser() {
		List<User> users = userMapper.listUser();
		log.info("users.size" + users.size());
		List<UserDTO> userDTOList = new ArrayList<>();
		userDTOList = users.stream().map(user -> {
			return new UserDTO(user.getUserName(), user.getUserCode());
		}).collect(Collectors.toList());
		return userDTOList;
	}
}
