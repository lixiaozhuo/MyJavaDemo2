package com.lixiaozhuo._08_association._01_one2one.pojo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 用户实体
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
	
	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

	@OneToOne(cascade=CascadeType.PERSIST)
	//维护一个外键
	@JoinColumn(name="roles_id")
	private Roles roles;

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
