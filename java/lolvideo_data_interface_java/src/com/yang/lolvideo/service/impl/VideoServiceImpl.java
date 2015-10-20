package com.yang.lolvideo.service.impl;

import java.util.List;

import com.yang.lolvideo.dao.VideoDao;
import com.yang.lolvideo.model.Video;
import com.yang.lolvideo.service.VideoService;

public class VideoServiceImpl implements VideoService {
	private VideoDao videoDao;
	public void setVideoDao(VideoDao videoDao) { 
		this.videoDao = videoDao;
	}
	public VideoDao getVideoDao(){
		return this.videoDao;
	}
	
	@Override
	public int getVideoCount(String title, String heros, String announcers,
			int video_catalog_id, String video_types, int episode_id) {
		// TODO Auto-generated method stub
		return this.videoDao.getVideoCount(title, heros, announcers, video_catalog_id, video_types, episode_id);
	}
	@Override
	public List<Video> getVideos(String title, String heros, String announcers,
			int video_catalog_id, String video_types, int episode_id,
			int start, int limit) {
		// TODO Auto-generated method stub
		if (start < 0 ) start = 0;
		if (limit < 0 ) limit = 20;
		return this.videoDao.getVideos(title, heros, announcers, video_catalog_id, video_types, episode_id, start, limit);
	}
	@Override
	public int getHeroVideoCount(String heros) {
		// TODO Auto-generated method stub
		return this.videoDao.getHeroVideoCount(heros);
	}
	@Override
	public List<Video> getHeroVideos(String heros, int start, int limit) {
		// TODO Auto-generated method stub
		if (start < 0 ) start = 0;
		if (limit < 0 ) limit = 20;
		return this.videoDao.getHeroVideos(heros, start, limit);
	}
	@Override
	public int getAnnouncerVideoCount(String announcers) {
		// TODO Auto-generated method stub
		return this.videoDao.getAnnouncerVideoCount(announcers);
	}
	@Override
	public List<Video> getAnnouncerVideos(String announcers, int start,
			int limit) {
		// TODO Auto-generated method stub
		if (start < 0 ) start = 0;
		if (limit < 0 ) limit = 20;
		return this.videoDao.getAnnouncerVideos(announcers, start, limit);
	}
	@Override
	public int getEpisodeVideoCount(int episode_id) {
		// TODO Auto-generated method stub
		return this.videoDao.getEpisodeVideoCount(episode_id);
	}
	@Override
	public List<Video> getEpisodeVideos(int episode_id, int start, int limit) {
		// TODO Auto-generated method stub
		if (start < 0 ) start = 0;
		if (limit < 0 ) limit = 20;
		return this.videoDao.getEpisodeVideos(episode_id, start, limit);
	}
	@Override
	public int getCatalogVideoCount(int catalog_id, String video_types) {
		// TODO Auto-generated method stub
		return this.videoDao.getCatalogVideoCount(catalog_id, video_types);
	}
	@Override
	public List<Video> getCatalogVideos(int catalog_id, String video_types,
			int start, int limit) {
		// TODO Auto-generated method stub
		if (start < 0 ) start = 0;
		if (limit < 0 ) limit = 20;
		return this.videoDao.getCatalogVideos(catalog_id, video_types, start, limit);
	}
	@Override
	public Video getVideoById(int id) {
		// TODO Auto-generated method stub
		return this.videoDao.getVideoById(id);
	}

	@Override
	public int getVideoCountByTags(String tags) {
		// TODO Auto-generated method stub
		return this.videoDao.getVideoCountByTags(tags);
	}
	@Override
	public List<Video> getVideosByTags(String tags, int start, int limit) {
		// TODO Auto-generated method stub
		if (start < 0 ) start = 0;
		if (limit < 0 ) limit = 20;
		return this.videoDao.getVideosByTags(tags, start, limit);
	}
	
	
	
}
