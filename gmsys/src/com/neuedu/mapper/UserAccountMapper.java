package com.neuedu.mapper;

import com.neuedu.model.UserAccount;

public interface UserAccountMapper {
	
	public UserAccount  findUserByAccount(String account);

}
