package com.taskboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.taskboard.dto.UserDto;
import com.taskboard.entity.User;
import com.taskboard.service.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	UserService userServiceImpl;
	 
	@PostMapping("/signUp")
	@ResponseBody
	public ResponseEntity<UserDto> signUp(@RequestBody User param) {
		UserDto userDto = userServiceImpl.signUp(param);
		return ResponseEntity.ok(userDto);
	}

	@PostMapping("/login")
	@ResponseBody
	public ResponseEntity<UserDto> login(@RequestBody User param) {
		UserDto userDto = userServiceImpl.login(param);
		return ResponseEntity.ok(userDto);
	}
	
}
