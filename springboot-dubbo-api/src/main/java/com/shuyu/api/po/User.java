package com.shuyu.api.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @package: com.shuyu.api.po
 * @className: User
 * @description:
 * @author: Shuyu.Wang
 * @date: 2019-05-05 17:35
 * @since: 0.1
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {
	private String userName;

	private String userCode;
}
