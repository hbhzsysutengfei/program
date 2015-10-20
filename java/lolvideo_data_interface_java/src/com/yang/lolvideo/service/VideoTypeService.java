package com.yang.lolvideo.service;

import java.util.List;

import com.yang.lolvideo.model.VideoType;

public interface VideoTypeService {
	public List<VideoType> getAllHeroTypes();
	public List<VideoType> getAllVideoTypes();
	public List<VideoType> getAllTypes();
	
	public VideoType getVideoTypeByName(String name);
}
