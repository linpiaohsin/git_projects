package com.neuedu.model;

import java.util.Date;

public class UserAccount {
	
	private int userId;
	private String userName;
	private String accounts;
	private String sex;
	private String password;
	private Date age;
	private String phone;
	private Date regDate;
	private int rid;
	private int departId;
	

	public UserAccount() {
		super();
	}

	public UserAccount(int userId, String userName, String accounts, String sex, String password, Date age,
			String phone, Date regDate, int rid, int departId) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.accounts = accounts;
		this.sex = sex;
		this.password = password;
		this.age = age;
		this.phone = phone;
		this.regDate = regDate;
		this.rid = rid;
		this.departId = departId;
	}
	
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAccounts() {
		return accounts;
	}
	public void setAccounts(String accounts) {
		this.accounts = accounts;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getAge() {
		return age;
	}
	public void setAge(Date age) {
		this.age = age;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getDepartId() {
		return departId;
	}
	public void setDepartId(int departId) {
		this.departId = departId;
	}
	
	
	
	

}
