package com.taskboard.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Comment {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;
	
	private String content;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "posts_id")
	private Posts posts;
	
	@Column(name="reg_user")
	private String regUser;
	
	@Column(name="reg_dts")
	private String regDts;
	
	@Column(name="mod_user")
	private String modUser;
	
	@Column(name="mod_dts")
	private String modDts;
	
	@Column(name="del_yn")
	private String delYn;
	
	@PrePersist
	public void prePersist() {
		
		if(this.regUser == null){
			this.regUser = "user";
		}
		
		if(this.regDts == null){
            this.regDts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
		
		if(this.delYn == null){
			this.delYn = "N";
		}
		
	}
	

}
