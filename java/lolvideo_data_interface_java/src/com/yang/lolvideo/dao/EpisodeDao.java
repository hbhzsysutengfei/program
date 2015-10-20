package com.yang.lolvideo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yang.lolvideo.model.Episode;

public interface EpisodeDao {
	public Episode			getEpisodeById(@Param("id") int id);
	public List<Episode> 	getEpisodes(@Param("start") int start, @Param("limit") int limit);
	public Episode			getEpisodeByTitle(@Param("title") String title);
}
