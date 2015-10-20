package com.yang.lolvideo.service.impl;

import java.util.List;

import com.yang.lolvideo.dao.HeroDao;
import com.yang.lolvideo.model.Hero;
import com.yang.lolvideo.service.HeroService;

public class HeroServiceImpl implements HeroService {
	private HeroDao heroDao;
	
	public void setHeroDao(HeroDao heroDao){
		this.heroDao = heroDao;
	}
	public HeroDao getHeroDao() {
		return this.heroDao;
	}
	
	@Override
	public int getHeroCount(String hero_types) {
		// TODO Auto-generated method stub
		//System.out.println("hero_types:" + hero_types);
		return this.heroDao.getHeroCount(hero_types);
	}
	@Override
	public List<Hero> getHeros(String hero_types, int start, int limit) {
		// TODO Auto-generated method stub
		//System.out.println("hero_types: " + hero_types + "  "+start +  " " + limit );
		return this.heroDao.getHeros(hero_types, start, limit);
	}
	@Override
	public Hero getHeroByName(String name) {
		// TODO Auto-generated method stub
		if (name == null || name.isEmpty()) return null;
		return this.heroDao.getHeroByName(name);
	}

}
