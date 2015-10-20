package com.yang.lolvideo.service;

import java.util.List;

import com.yang.lolvideo.model.Announcer;

public interface AnnouncerService {
	public int getAnnouncerCount();
	public List<Announcer> getAnnouncers(int start, int limit);
	public Announcer getAnouncerByName(String name);
}
