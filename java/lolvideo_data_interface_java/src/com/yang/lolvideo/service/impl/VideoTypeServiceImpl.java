package com.yang.lolvideo.service.impl;

import java.util.List;

import com.yang.lolvideo.dao.VideoTypeDao;
import com.yang.lolvideo.model.VideoType;
import com.yang.lolvideo.service.VideoTypeService;

public class VideoTypeServiceImpl implements VideoTypeService {
	private VideoTypeDao videoTypeDao;
	
	public void setVideoTypeDao(VideoTypeDao videoTypeDao){
		this.videoTypeDao = videoTypeDao;
	}
	public VideoTypeDao getVideoTypeDao(){
		return this.videoTypeDao;
	}
	@Override
	public List<VideoType> getAllHeroTypes() {
		// TODO Auto-generated method stub
		return this.videoTypeDao.getAllHeroTypes();
	}

	@Override
	public List<VideoType> getAllVideoTypes() {
		// TODO Auto-generated method stub
		return this.videoTypeDao.getAllVideoTypes();
	}

	@Override
	public List<VideoType> getAllTypes() {
		// TODO Auto-generated method stub
		return this.videoTypeDao.getAllTypes();
	}
	@Override
	public VideoType getVideoTypeByName(String name) {
		// TODO Auto-generated method stub
		if(name == null || name.isEmpty()) return null;
		return this.videoTypeDao.getVideoTypeByName(name);
	}

}
