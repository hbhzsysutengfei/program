package com.yang.lolvideo.service.impl;

import java.util.List;

import com.yang.lolvideo.dao.EpisodeDao;
import com.yang.lolvideo.model.Episode;
import com.yang.lolvideo.service.EpisodeService;

public class EpisodeServiceImpl implements EpisodeService {
	private EpisodeDao episodeDao;
	public void setEpisodeDao(EpisodeDao episodeDao){
		this.episodeDao = episodeDao;
	}
	public EpisodeDao getEpisodeDao(){
		return this.episodeDao;
	}

	@Override
	public List<Episode> getEpisode(int start, int limit) {
		// TODO Auto-generated method stub
		if(start<0) start = 0;
		if(limit < 0) limit = 20;
		return this.episodeDao.getEpisodes(start, limit);
	}
	@Override
	public Episode getEpisodeByID(int id) {
		// TODO Auto-generated method stub
		return this.episodeDao.getEpisodeById(id);
	}
	@Override
	public Episode getEpisodeByTitle(String title) {
		// TODO Auto-generated method stub
		if (title == null || title.isEmpty()) return null;
		return this.episodeDao.getEpisodeByTitle(title);
	}
}
