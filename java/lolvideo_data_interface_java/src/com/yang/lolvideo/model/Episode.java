package com.yang.lolvideo.model;

import org.json.JSONObject;

import com.yang.lolvideo.json.JsonData;

public class Episode implements JsonData{
	private int id;
	private String title;
	private String snapshot;
	
	public void setId(int id){ this.id = id;}
	public int getId(){ return this.id;}
	public void setTitle(String title){ this.title = title;}
	public String getTitle(){ return this.title;}
	public void setSnapshot(String snapshot){this.snapshot = snapshot;}
	public String getSnapshot(){return this.snapshot;}
	@Override
	public JSONObject getJSONObject() {
		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();
		result.put("id", this.id);
		result.put("title", this.title);
		result.put("snapshot", this.snapshot);
		return result;
	}

}
