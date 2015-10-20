package com.yang.lolvideo.service.impl;

import com.yang.lolvideo.dao.VersionDao;
import com.yang.lolvideo.model.Version;
import com.yang.lolvideo.service.VersionService;

public class VersionServiceImpl implements VersionService {
	
	private VersionDao versionDao;
	public void setVersionDao(VersionDao versionDao){
		this.versionDao = versionDao;
	}
	public VersionDao getVersionDao(){
		return this.versionDao;
	}
	
	@Override
	public Version getLatestVersion(String platform) {
		// TODO Auto-generated method stub
		if(platform.equalsIgnoreCase(PLATFORM_ANDROID) || platform.equalsIgnoreCase(PLATFORM_IOS)){
			return this.versionDao.getLatestVersion(platform);
		}
		return this.versionDao.getLatestVersion(PLATFORM_ANDROID);
		
	}

}
