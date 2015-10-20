package com.yang.lolvideo.model;

import java.util.Date;

public class UserLog {
	private String ip;
	private String url;
	private String query;
	private Date created_at;
	private Date updated_at;
	private String version;
	private String platform;
	private String mac;
	
	public void setIp(String ip){ this.ip = ip;}
	public String getIp(){return this.ip;}
	public void setUrl(String url){ this.url = url;}
	public String getUrl () { return this.url;}
	public void setQuery(String query) { this.query = query;}
	public String getQuery() { return this.query;}
	public void setCreated_at(Date date){ this.created_at = date;}
	public Date getCreated_at(){ return this.created_at;}
	public void setUpdate_at(Date date){ this.updated_at = date;}
	public Date getUpdate_ad(){ return this.updated_at;}
	public void setVersion(String version){ this.version = version;}
	public String getVersion(){ return this.version;}
	public void setPlatform(String platform){this.platform = platform;}
	public String getPlatform(){return this.platform;}
	public void setMac(String mac){this.mac = mac;}
	public String getMac(){ return this.mac;}
	
}
