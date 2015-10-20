package com.yang.lolvideo.service.impl;

import com.yang.lolvideo.dao.UserKeywordDao;
import com.yang.lolvideo.model.UserKeyword;
import com.yang.lolvideo.service.UserKeywordService;

public class UserKeywordServiceImpl implements UserKeywordService{
	private UserKeywordDao userKeywordDao;
	public void setUserKeywordDao(UserKeywordDao userKeywordDao){
		this.userKeywordDao = userKeywordDao;
	}
	public UserKeywordDao getUserKeywordDao(){
		return this.userKeywordDao;
	}
	@Override
	public int insert(UserKeyword userKeyword) {
		// TODO Auto-generated method stub
		return this.userKeywordDao.insert(userKeyword);
	}

}
