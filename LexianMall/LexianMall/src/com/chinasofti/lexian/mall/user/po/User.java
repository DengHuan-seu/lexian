package com.chinasofti.lexian.mall.user.po;

import java.io.Serializable;
import java.sql.Timestamp;

public class User  implements Serializable{
	private static final long serialVersionUID = -4104941611225974630L;
	private String id;
	private String sex;
	private String username;
	private String phone;
	private String mail;
	private String portrait;
	private transient String passwd;
	private Integer status;
	private Timestamp lastlogintime;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status; 
	}

	public Timestamp getLastlogintime() {
		return lastlogintime;
	}

	public void setLastlogintime(Timestamp lastlogintime) {
		this.lastlogintime = lastlogintime;
	}

	private String sessionKey;
	
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	
	public String getSessionKey() {
		return sessionKey;
	}
	
	public String getFullPortrait(){
		return com.chinasofti.lexian.mall.common.util.Config.PicServerVirtualPath + this.portrait;
	}
	
}
