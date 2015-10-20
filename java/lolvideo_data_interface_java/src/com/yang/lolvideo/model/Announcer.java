package com.yang.lolvideo.model;

import org.json.JSONObject;

import com.yang.lolvideo.json.JsonData;

public class Announcer implements JsonData {
	private int id;
	private String name;
	private String face;
	private String face1;
	private String face2;
	
	public void setId(int id){ this.id = id;}
	public int getId(){ return this.id;}
	public void setName(String name){ this.name = name;}
	public String getName(){ return this.name;}
	public void setFace(String face) { this.face = face;}
	public String getFace(){ return this.face;}
	public void setFace1(String face1){ this.face1 = face1;}
	public String getFace1(){ return this.face1;}
	public void setFace2(String face2) { this.face2 = face2;}
	public String getFace2(){ return this.face2;}
	
	@Override
	public JSONObject getJSONObject() {
		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();
		result.put("id", this.id);
		result.put("name", this.name);
		result.put("face", this.face);
		result.put("face1", this.face1);
		result.put("face2", this.face2);
		return result;
	}

}
