package com.shuyu.service.controller;

import com.shuyu.api.dto.UserDTO;
import com.shuyu.api.service.UserService;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @package: com.shuyu.service.controller
 * @className: TestController
 * @description:
 * @author: Shuyu.Wang
 * @date: 2019-05-05 16:31
 * @since: 0.1
 **/
@RestController
@RequestMapping("")
public class TestController {

	@Autowired
	private UserService userService;

	@GetMapping("service")
	public List<UserDTO> hello() {
		return userService.listUser();
	}
}
