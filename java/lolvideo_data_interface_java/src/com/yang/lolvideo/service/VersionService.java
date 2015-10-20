package com.yang.lolvideo.service;

import com.yang.lolvideo.model.Version;

public interface VersionService {
	public static final String  PLATFORM_ANDROID="android";
	public static final String PLATFORM_IOS="ios";
	public Version getLatestVersion(String platform);
}
