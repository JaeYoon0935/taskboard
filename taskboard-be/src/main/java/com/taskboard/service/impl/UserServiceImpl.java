package com.taskboard.service.impl;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.taskboard.dto.UserDto;
import com.taskboard.entity.User;
import com.taskboard.repository.UserRepository;
import com.taskboard.service.UserService;
import com.taskboard.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService{
	
	//@Autowired
	//private UserRepository userRepository;
	
	private final UserRepository userRepository; //@Autowired 대신 생성자 주입(@RequiredArgsConstructor) 사용
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;
	
	 @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }
	
	 @Override
	 public UserDto signUp(User param) {
	     User user = new User();
	     user.setUserName(param.getUserName());
	     user.setEmail(param.getEmail());
	     user.setPassword(passwordEncoder.encode(param.getPassword()));  // 비밀번호 암호화 후 저장
	     user.setBirth(param.getBirth());
	     user.setPhoneNumber(param.getPhoneNumber());
	     user = userRepository.save(user);

	     return UserDto.fromEntity(user, null);
	 }

	@Override
	public UserDto login(User param) {
		String email = param.getEmail();
		String password = param.getPassword();
		User user = userRepository.findByEmail(email)
			    .orElseThrow(() -> new UsernameNotFoundException("Invalid email or password"));

		if (!passwordEncoder.matches(password, user.getPassword())) {
		    throw new BadCredentialsException("Invalid email or password");
		}
		
	    String token = jwtUtil.generateToken(user.getEmail()); // JWT 토큰 발급

	    return new UserDto(user.getUserName(), user.getEmail(), token);
		//return UserDto.fromEntity(user);
	}
	
}
