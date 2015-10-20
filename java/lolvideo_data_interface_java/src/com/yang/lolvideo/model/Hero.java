package com.yang.lolvideo.model;

import org.json.JSONObject;

import com.yang.lolvideo.json.JsonData;

public class Hero implements JsonData {
	private int 	id;
	private String 	name;
	private String 	nick;
	private String 	face;
	private String 	face1;
	private String 	face2;
	private String 	hero_types;
	private int 	flag;
	
	public void setId(int id){ this.id = id;}
	public int getId() { return this.id;}
	public void setName(String name){ this.name = name;}
	public String getName(){ return this.name;}
	public void setNick(String nick){ this.nick = nick;}
	public String getNick(){ return this.nick;}
	public void setFace(String face){ this.face=face;}
	public String getFace(){return this.face;}
	public void setFace1(String face1){ this.face1=face1;}
	public String getFace1(){return this.face1;}
	public void setFace2(String face2){ this.face2=face2;}
	public String getFace2(){return this.face2;}
	public void setHero_types(String hero_types){ this.hero_types = hero_types;}
	public String getHero_types(){ return this.hero_types;}
	public void setFlag(int flag){ this.flag = flag;}
	public int getFlag(){ return this.flag;}
	
	@Override
	public JSONObject getJSONObject(){
		JSONObject result = new JSONObject();
		result.put("id", this.id);
		result.put("name", this.name);
		result.put("nick", this.nick);
		result.put("face", this.face);
		result.put("face1",this.face1);
		result.put("face2", this.face2);
		result.put("hero_types", this.hero_types);
		result.put("flag", this.flag);
		return result;
	}
	
	
	
}
