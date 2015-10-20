package com.yang.lolvideo.model;

import org.json.JSONObject;

import com.yang.lolvideo.json.JsonData;

public class Catalog implements JsonData {
	private int id;
	private String code;
	private String name;
	
	public void setId(int id){ this.id = id;}
	public int getId(){ return this.id;}
	public void setCode(String code){ this.code = code;}
	public String getCode(){ return this.code;}
	public void setName(String name){ this.name = name;}
	public String getName(){ return this.name;}
	@Override
	public JSONObject getJSONObject() {
		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("code", this.code);
		result.put("name", this.name);
		return result;
	}

}
