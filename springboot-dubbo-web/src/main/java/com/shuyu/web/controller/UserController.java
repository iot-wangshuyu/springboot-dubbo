package com.shuyu.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shuyu.api.dto.UserDTO;
import com.shuyu.api.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @package: com.shuyu.web.controller
 * @className: UserController
 * @description:
 * @author: Shuyu.Wang
 * @date: 2019-05-05 16:31
 * @since: 0.1
 **/
@RestController
@RequestMapping("user")
public class UserController {

	@Reference(version = "1.0.0")
	private UserService userService;

	@RequestMapping("hello")
	public List<UserDTO> hello() {
		List<UserDTO> userDTOList = userService.listUser();
		return userDTOList;
	}
}
