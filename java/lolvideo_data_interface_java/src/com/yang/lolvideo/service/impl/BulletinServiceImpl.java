package com.yang.lolvideo.service.impl;

import java.util.List;

import com.yang.lolvideo.dao.BulletinDao;
import com.yang.lolvideo.model.Bulletin;
import com.yang.lolvideo.service.BulletinService;

public class BulletinServiceImpl implements BulletinService {
	private BulletinDao bulletinDao;
	
	public void setBulletinDao(BulletinDao bulletinDao){
		this.bulletinDao = bulletinDao;
	}
	public BulletinDao getBulletinDao(){
		return this.bulletinDao;
	}

	@Override
	public List<Bulletin> getBulletins(int start, int limit) {
		// TODO Auto-generated method stub
		if(start < 0) start =0;
		if (limit < 0 ) limit = 6;
		return this.bulletinDao.getBulletins(start, limit);
	}

}
