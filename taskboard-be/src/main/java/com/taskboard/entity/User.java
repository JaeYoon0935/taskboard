package com.taskboard.entity;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements UserDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    
    @Column(name="user_name")
    private String userName;
    
    private String password;
    
    private String email;

    private String birth;
    
    @Column(name="phone_number")
    private String phoneNumber;
    
    /*
    @Enumerated(EnumType.STRING)
    private Role role; // 사용자 역할 추가 (USER, ADMIN 등)
    */
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //return List.of(() -> "ROLE_" + role.name()); // ROLE_USER, ROLE_ADMIN
        return List.of(() -> "USER_ROLE"); // 기본 역할을 직접 하드코딩 (DB 저장 X)
    }

    @Override
    public String getUsername() {
        return this.email; // Spring Security에서 username을 email로 사용
    }

    public String getEmail() {
        return this.email;
    }

    public String getUserName() {
        return this.userName;
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}