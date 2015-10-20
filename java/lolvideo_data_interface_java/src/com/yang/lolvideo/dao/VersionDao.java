package com.yang.lolvideo.dao;

import org.apache.ibatis.annotations.Param;

import com.yang.lolvideo.model.Version;

public interface VersionDao {
	public Version getLatestVersion(@Param("platform")String platform);
}
