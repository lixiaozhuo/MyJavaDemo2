package com.lixiaozhuo._07_custom_repository.pojo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 实体类
 */
@Entity
@Table(name="t_users")
public class Users implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//主键自增长
	@Column(name="userId")
	private Integer userId;
	
	@Column(name="username")
	private String username;
	
	@Column(name="userAge")
	private Integer userAge;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getUserAge() {
		return userAge;
	}

	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", username=" + username + ", userAge=" + userAge + "]";
	}
	
}
