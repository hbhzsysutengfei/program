package com.yang.lolvideo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yang.lolvideo.model.Video;

public interface VideoService {
	public int getVideoCount(
			String 		title,// like	
			String 		heros,// like
			String 		announcers,// like
			int 		video_catalog_id, // =
			String		video_types, // like
			int			episode_id // = 
			);
	public List<Video> getVideos(
			String 		title,// like	
			String 		heros,// like
			String 		announcers,// like
			int 		video_catalog_id, // =
			String		video_types, // like
			int			episode_id, // = 
			int 		start, 
			int 		limit);
	
	public 	int 			getHeroVideoCount(String heros);
	public 	List<Video> 	getHeroVideos(String heros, int start, int limit);
	public 	int 			getAnnouncerVideoCount(String announcers);
	public 	List<Video> 	getAnnouncerVideos(String announcers, int start, int limit);
	public 	int 			getEpisodeVideoCount(int episode_id);
	public 	List<Video> 	getEpisodeVideos(int episode_id, int start, int limit);
	public 	int 			getCatalogVideoCount(int catalog_id, String video_types);
	public 	List<Video> 	getCatalogVideos(int catalog_id,String video_types, int start, int limit);
	public 	Video 			getVideoById(int id);
	
	public 	int				getVideoCountByTags(String tags);
	public	List<Video>		getVideosByTags(String tags,int start, int limit);
}
