package com.shuyu.service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@GetMapping("service")
	public String hello(){
		return "Hello Service";
	}
}
