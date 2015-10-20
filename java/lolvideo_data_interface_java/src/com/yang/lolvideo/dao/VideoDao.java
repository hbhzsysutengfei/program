package com.yang.lolvideo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yang.lolvideo.model.Video;

public interface VideoDao {
	public int getVideoCount(
			@Param("title")				String 		title,// like
			@Param("heros")				String 		heros,// like
			@Param("announcers")		String 		announcers,// like
			@Param("video_catalog_id") 	int 		video_catalog_id, // =
			@Param("video_types")		String		video_types, // like
			@Param("episode_id")		int			episode_id);
	
	public List<Video> getVideos(
			@Param("title")				String 		title,// like	
			@Param("heros")				String 		heros,// like
			@Param("announcers")		String 		announcers,// like
			@Param("video_catalog_id") 	int 		video_catalog_id, // =
			@Param("video_types")		String		video_types, // like
			@Param("episode_id")		int			episode_id, // = 
			@Param("start")				int 		start, 
			@Param("limit") 			int 		limit);
	
	
	public int getHeroVideoCount(
			@Param("heros")				String 		heros);
	public List<Video> getHeroVideos(
			@Param("heros")				String 		heros, 
			@Param("start")				int 		start,
			@Param("limit") 			int 		limit);
	
	public int getAnnouncerVideoCount(
			@Param("announcers")		String 		announcers);
	public List<Video> getAnnouncerVideos(
			@Param("announcers")		String 		announcers, 
			@Param("start")				int 		start,
			@Param("limit") 			int 		limit);
	
	public int getEpisodeVideoCount(
			@Param("episode_id")		int			episode_id);	
	public List<Video> getEpisodeVideos(
			@Param("episode_id")		int			episode_id, 
			@Param("start")				int 		start,
			@Param("limit") 			int 		limit);
	
	public int getCatalogVideoCount(
			@Param("catalog_id")		int			catalog_id,
			@Param("video_types")		String		video_types);
	public List<Video> getCatalogVideos(
			@Param("catalog_id")		int			catalog_id,
			@Param("video_types")		String		video_types,
			@Param("start")				int 		start,
			@Param("limit")				int			limit);
	public Video getVideoById(
			@Param("id")				int			id);
	
	public int getVideoCountByTags(
			@Param("tags")				String		tags);
	public List<Video> getVideosByTags(
			@Param("tags")				String		tags,
			@Param("start")				int			start,
			@Param("limit")				int			limit);
	
	
	
	public List<Video> getVideoByKeys(
			@Param("key")				String		keys, 
			@Param("start")				int 		start,
			@Param("limit") 			int 		limit);
	
}
