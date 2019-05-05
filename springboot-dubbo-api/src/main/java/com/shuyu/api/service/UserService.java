package com.shuyu.api.service;

import com.shuyu.api.dto.UserDTO;

import java.util.List;

/**
 * @package: com.shuyu.api.service
 * @className: UserService
 * @description:
 * @author: Shuyu.Wang
 * @date: 2019-05-05 16:25
 * @since: 0.1
 **/
public interface UserService {

	List<UserDTO> listUser();
}
