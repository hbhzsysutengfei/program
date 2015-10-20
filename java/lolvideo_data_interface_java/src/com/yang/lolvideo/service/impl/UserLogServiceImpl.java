package com.yang.lolvideo.service.impl;

import com.yang.lolvideo.dao.UserLogDao;
import com.yang.lolvideo.model.UserLog;
import com.yang.lolvideo.service.UserLogService;

public class UserLogServiceImpl implements UserLogService {
	private UserLogDao userLogDao;
	
	public void setUserLogDao(UserLogDao userLogDao){
		this.userLogDao = userLogDao;
	}
	public UserLogDao getUserLogDao(){
		return this.userLogDao;
	}
	@Override
	public int insert(UserLog userLog) {
		// TODO Auto-generated method stub
		return this.userLogDao.insert(userLog);
	}
	
}
