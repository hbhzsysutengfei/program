package com.yang.lolvideo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yang.lolvideo.model.Hero;

public interface HeroDao {
	
	public int getHeroCount(@Param("hero_types") String hero_types);
	
	public List<Hero> getHeros(@Param("hero_types") String hero_types, @Param("start") int start,@Param("limit") int limit);
	
	public Hero getHeroByName(@Param("name") String name);
	
}
