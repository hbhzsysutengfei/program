package com.yang.lolvideo.model;

import java.util.Date;

public class UserKeyword {
	private int id;
	private String keyword;
	private Date created_at;
	private Date updated_at;
	
	public void setId(int id){ this.id = id;}
	public int getId(){ return this.id;}
	public void setKeyword(String keyword){this.keyword = keyword;}
	public String getKeyword(){ return this.keyword;}
	public void setCreated_at(Date created_at){ this.created_at = created_at;}
	public Date getCreated_at(){ return this.created_at;}
	public void setUpdated_at(Date updated_at){ this.updated_at = updated_at;}
	public Date getUpdated_at(){ return this.updated_at;}
}
