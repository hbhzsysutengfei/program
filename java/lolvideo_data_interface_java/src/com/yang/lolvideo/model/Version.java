package com.yang.lolvideo.model;

import org.json.JSONObject;

import com.yang.lolvideo.json.JsonData;

public class Version implements JsonData {
	private String version; // "v"
	private int version_code; // "vc" 
	private String download_url; // "url"
	private String platform; // "platform"
	
	public void setVersion(String version){this.version = version;}
	public String getVersion(){ return this.version;}
	public void setVersion_code(int version_code) {this.version_code = version_code;}
	public int getVersion_code(){ return this.version_code;}
	public void setUrl(String download_url){ this.download_url = download_url;}
	public String getUrl(){ return this.download_url;}
	public void setPlatform(String platform){this.platform = platform;}
	public String getPlatform(){ return this.platform;}

	@Override
	public JSONObject getJSONObject() {
		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();
		result.put("v", this.version);
		result.put("vc", this.version_code);
		result.put("url", this.download_url);
		result.put("platform", this.platform);
		return result;
	}

}
