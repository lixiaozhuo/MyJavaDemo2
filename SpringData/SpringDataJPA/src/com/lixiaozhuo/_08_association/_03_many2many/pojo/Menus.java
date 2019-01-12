package com.lixiaozhuo._08_association._03_many2many.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 菜单实体
 *
 */
@Entity
@Table(name="t_menus")
public class Menus {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="menusId")
	private Integer menusId;
	
	@Column(name="menusName")
	private String menusName;
	
	@Column(name="menusUrl")
	private String menusUrl;
	
	@Column(name="fatherId")
	private Integer fatherId;

	@ManyToMany(mappedBy="menus")
	private Set<Roles> roles = new HashSet<>();

	public Integer getMenusId() {
		return menusId;
	}

	public void setMenusId(Integer menusId) {
		this.menusId = menusId;
	}

	public String getMenusName() {
		return menusName;
	}

	public void setMenusName(String menusName) {
		this.menusName = menusName;
	}

	public String getMenusUrl() {
		return menusUrl;
	}

	public void setMenusUrl(String menusUrl) {
		this.menusUrl = menusUrl;
	}

	public Integer getFatherId() {
		return fatherId;
	}

	public void setFatherId(Integer fatherId) {
		this.fatherId = fatherId;
	}

	public Set<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Menus [menusId=" + menusId + ", menusName=" + menusName + ", menusUrl=" + menusUrl + ", fatherId="
				+ fatherId + "]";
	}
}
