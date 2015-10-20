package com.yang.lolvideo.service;

import java.util.List;

import com.yang.lolvideo.model.Hero;

public interface HeroService {
	public int 			getHeroCount(String hero_types);
	public List<Hero> 	getHeros(String hero_types, int start, int limit);
	public Hero			getHeroByName(String name);
}
