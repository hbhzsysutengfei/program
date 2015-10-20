package com.yang.lolvideo.service.impl;

import java.util.List;

import com.yang.lolvideo.dao.CatalogDao;
import com.yang.lolvideo.model.Catalog;
import com.yang.lolvideo.service.CatalogService;

public class CatalogServiceImpl implements CatalogService {
	private CatalogDao catalogDao;
	
	public void setCatalogDao(CatalogDao catalogDao){
		this.catalogDao = catalogDao;
	}
	public CatalogDao getCatalogDao(){
		return this.catalogDao;
	}

	@Override
	public List<Catalog> getCatalogs() {
		// TODO Auto-generated method stub
		return this.catalogDao.getCatalogs();
	}

}
