package com.yang.lolvideo.service;

import java.util.List;

import com.yang.lolvideo.model.Bulletin;

public interface BulletinService {
	public List<Bulletin> getBulletins(int start, int limit);
}
