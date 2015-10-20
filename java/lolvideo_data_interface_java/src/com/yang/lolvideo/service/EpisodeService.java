package com.yang.lolvideo.service;

import java.util.List;

import com.yang.lolvideo.model.Episode;

public interface EpisodeService {
	public List<Episode> getEpisode(int start, int limit);
	public Episode getEpisodeByID(int id);
	public Episode getEpisodeByTitle(String title);

}
