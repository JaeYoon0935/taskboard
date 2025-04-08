package com.taskboard.service;

import com.taskboard.dto.UserDto;
import com.taskboard.entity.User;
public interface UserService {
	
	public UserDto signUp(User param);
	
	public UserDto login(User param);
}
