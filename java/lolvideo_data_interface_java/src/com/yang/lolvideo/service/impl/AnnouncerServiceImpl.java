package com.yang.lolvideo.service.impl;

import java.util.List;

import com.yang.lolvideo.dao.AnnouncerDao;
import com.yang.lolvideo.model.Announcer;
import com.yang.lolvideo.service.AnnouncerService;

public class AnnouncerServiceImpl implements AnnouncerService {
	private AnnouncerDao announcerDao;
	
	public void setAnnouncerDao(AnnouncerDao announcerDao){
		this.announcerDao = announcerDao;
	}
	public AnnouncerDao getAnnouncerDao(){
		return this.announcerDao;
	}

	@Override
	public int getAnnouncerCount() {
		// TODO Auto-generated method stub
		return this.announcerDao.getAnnouncerCount();
	}

	@Override
	public List<Announcer> getAnnouncers(int start, int limit) {
		// TODO Auto-generated method stub
		if (start < 0 ) start = 0;
		if (!(limit >0)) limit = 25;
		return this.announcerDao.getAnnouncers(start, limit);
	}
	@Override
	public Announcer getAnouncerByName(String name) {
		// TODO Auto-generated method stub
		if(name == null || name.isEmpty()) return null;
		return this.announcerDao.getAnnouncerByName(name);
	}

}
