package com.yang.lolvideo.model;

import org.json.JSONObject;

import com.yang.lolvideo.json.JsonData;

public class Bulletin implements JsonData{
	private String title;
	private String snapshot;
	private String snapshot1;
	private String snapshot2;
	private int video_unit_id;
	
	public void setTitle(String title){ this.title = title;}
	public String getTitle(){ return this.title;}
	public void setSnapshot(String snapshot) { this.snapshot = snapshot;}
	public String getSnapshot(){ return this.snapshot;}
	public void setSnapshot1(String snapshot1) { this.snapshot1 = snapshot1;}
	public String getSnapshot1(){ return this.snapshot1;}
	public void setSnapshot2(String snapshot2) { this.snapshot2 = snapshot2;}
	public String getSnapshot2(){ return this.snapshot2;}
	public void setVideo_unit_id(int video_unit_id){ this.video_unit_id = video_unit_id;}
	public int getvideo_unit_id() { return this.video_unit_id;}
	
	@Override
	public JSONObject getJSONObject() {
		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();
		result.put("title", this.title);
		result.put("snapshot", this.snapshot);
		result.put("snapshot1",	this.snapshot1);
		result.put("snapshot2", this.snapshot2);
		result.put("video_unit_id", this.video_unit_id);
		return result;
	}
	
	

}
