package com.neosoft.springbootsecurity.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {

	private Integer userId;
	private String password;
	private String userName;

}
