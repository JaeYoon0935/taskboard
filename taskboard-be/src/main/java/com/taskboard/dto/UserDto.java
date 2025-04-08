package com.taskboard.dto;

import com.taskboard.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	
	private Long userId;
	private String userName;
	private String email;
	private String password;
	private String birth;
	private String phoneNumber;
	private String token;
	
	public static UserDto fromEntity(User user, String token) {
		return new UserDto(
			user.getUserId(),
			user.getUserName(),
			user.getEmail(),
			user.getPassword(),
			user.getBirth(),
			user.getPhoneNumber(),
			token
		);
	}

    public UserDto(String userName, String email, String token) {
        this.userName = userName;
        this.email = email;
        this.token = token;
    }
	

}
