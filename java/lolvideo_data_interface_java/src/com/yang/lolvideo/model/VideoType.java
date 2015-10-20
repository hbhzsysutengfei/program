package com.yang.lolvideo.model;

import org.json.JSONObject;

import com.yang.lolvideo.json.JsonData;

public class VideoType implements JsonData {
	private static final String TYPE_HEROS = "heros";
	private static final String TYPE_VIDEO = "video";
	private int id;
	private String name;
	private String type;
	
	public void setId(int id){this.id = id;}
	public int getId(){return this.id;}
	public void setName(String name){ this.name = name;}
	public String getName(){ return this.name;}
	public void setType(String type){ this.type = type;}
	public String getType(){return this.type;}
	@Override
	public JSONObject getJSONObject(){
		JSONObject result = new JSONObject();
		result.put("id", this.id);
		result.put("name", this.name);
		result.put("type", this.type);		
		return result;
	}
}
