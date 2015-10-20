package com.yang.lolvideo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yang.lolvideo.model.Announcer;

public interface AnnouncerDao {
	public int getAnnouncerCount();
	
	public List<Announcer> getAnnouncers(@Param("start") int start, @Param("limit") int limit);
	
	public Announcer getAnnouncerByName(@Param("name") String name);
}
