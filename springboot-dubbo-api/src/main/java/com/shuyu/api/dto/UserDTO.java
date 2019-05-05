package com.shuyu.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @package: com.shuyu.api.dto
 * @className: UserDTO
 * @description:
 * @author: Shuyu.Wang
 * @date: 2019-05-05 16:19
 * @since: 0.1
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO implements Serializable {

	private String userName;

	private String userCode;
}
