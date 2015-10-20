package com.yang.lolvideo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yang.lolvideo.model.VideoType;

public interface VideoTypeDao {
	
	//@Select("select id, type_name as name, cat_code as type from video_types where visible=1 and cat_code='heros' order by priority desc, updated_at desc;")
	public List<VideoType> getAllHeroTypes();
	
	//@Select("select id, type_name as name, cat_code as type from video_types where visible=1 and cat_code='video' order by priority desc, updated_at desc;")
	public List<VideoType> getAllVideoTypes();
	
	//@Select("select id, type_name as name, cat_code ad type from video_types where visible=1 ordr by priority desc, updated_at desc")
	public List<VideoType> getAllTypes();
	
	public VideoType		getVideoTypeByName(@Param("name") String name);
}
