package com.yang.lolvideo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yang.lolvideo.model.Bulletin;

public interface BulletinDao {
	public List<Bulletin> getBulletins(@Param("start")int start,@Param("limit") int limit);
}
