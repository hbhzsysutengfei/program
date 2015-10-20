package com.yang.lolvideo.model;

import java.util.Date;

import org.json.JSONObject;

import com.yang.lolvideo.json.JsonData;

public class Video implements JsonData {
	private int id;
	private String title;
	private String desc;
	private String tags;
	private int episode_id;
	private String target_url;
	private int video_catalog_id;
	private int hits;
	private String snapshot;
	private String snapshot1;
	private String snapshot2;
	private Date updated_at;
	private String convert_params;
	private int comments;
	private String announcers;
	
	/*
	 *SQL_VIDEO_BY_ID = "
	 *	select id,title,`desc`,tags,episode_id, target_url,snapshot,snapshot1,snapshot2, convert_params,comments
	 *  from video_units where id = ?";
	 *var SQL_MAIN_CATALOG_VIDEOS = "(
	 *	select id,title,video_catalog_id,hits,snapshot,snapshot1,snapshot2,updated_at,comments 
	 *	from video_units 
	 *	where video_catalog_id = ? and visible = 1 order by priority desc, updated_at desc limit ?)";
	 *
	 *
	 *var SQL_VIDEO_EPISODES= 
	 *	select id,title,snapshot,snapshot1,snapshot2,updated_at,hits,comments 
	 *	from video_units where episode_id = ? order by updated_at desc limit ?,?
	 *var SQL_SEARCH_VIDEOS = "
	 *	select id,title,hits,snapshot,snapshot1,snapshot2,updated_at,comments 
	 *	from video_units 
	 *	where visible = 1 %s order by priority desc, updated_at desc limit ?,?";
	 *var SQL_ANNOUNCER_VIDEOS
	 *	select id,title,hits,snapshot,snapshot1,snapshot2,updated_at,comments 
	 *	from video_units 
	 *	where visible = 1 and announcers like ? %s order by priority desc, updated_at desc limit ?,?
	 *var SQL_HERO_VIDEOS=
	 *	select id,title,hits,snapshot,snapshot1,snapshot2,updated_at,comments 
	 *	from video_units 
	 *	where visible = 1 and heros like ? %s order by priority desc, updated_at desc limit ?,?
	 */
	
	public void setId(int id){ this.id = id;}
	public int getId(){ return this.id;}
	public void setTitle(String title){ this.title = title;}
	public String getTitle(){ return this.title;}
	public void setDesc(String desc) { this.desc = desc;}
	public String getDesc(){ return this.desc;}
	public void setTags(String tags){ this.tags = tags;}
	public String getTags(){ return this.tags;}
	public void setEpisode_id(int episode_id){ this.episode_id = episode_id;}
	public int getEpisode_id(){ return this.episode_id;}
	public void setTarget_url(String target_url){ this.target_url = target_url;}
	public String getTarget_url(){ return this.target_url;}
	public void setConvert_params(String convert_params){ this.convert_params = convert_params;}
	public String getConvert_params(){ return this.convert_params;}
	public void setVideo_catalog_id (int video_catalog_id){ this.video_catalog_id = video_catalog_id;}
	public int getVideo_catalog_id(){ return this.video_catalog_id;}
	public void setHits(int hits){ this.hits = hits;}
	public int getHits(){ return this.hits;}
	public void setSnapshot(String snapshot){ this.snapshot = snapshot;}
	public String getSnapshot(){ return this.snapshot;}
	public void setSnapshot1(String snapshot1){ this.snapshot1 =snapshot1;}
	public String getSnapshot1(){ return this.snapshot1;}
	public void setSnapshot2(String snapshot2){ this.snapshot2 = snapshot2;}
	public String getSnapshot2(){ return this.snapshot2;}
	public void setUpdated_at(Date updated_at){this.updated_at = updated_at;}
	public Date getUpdated_at(){ return this.updated_at;}
	public void setComments(int comments){ this.comments = comments;}
	public int getComments(){ return this.comments; }
	public void setAnouncers(String anouncers){this.announcers = announcers;}
	public String getAnouncers(){return this.announcers;}

	/*
	 * SQL_SEARCH_VIDEOS, SQL_HERO_VIDEOS, SQL_ANNOUNCER_VIDEOS
	 */
	@Override
	public JSONObject getJSONObject() {
		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();
		result.put("id", this.id);
		result.put("title", this.title);
		result.put("hits", this.hits);
		result.put("snapshot", this.snapshot);
		result.put("snapshot1", this.snapshot1);
		result.put("snapshot2", this.snapshot2);
		result.put("updated_at",this.updated_at.getTime());
		result.put("comments", this.comments);
		return result;
	}
	
	public JSONObject getJSONObjectForID(){
		/*
		 * id,title,`desc`,tags,episode_id, target_url,snapshot,snapshot1,snapshot2, convert_params,comments
		 */
		JSONObject result = new JSONObject();
		result.put("id", this.id);
		result.put("title", this.title);
		result.put("desc", this.desc);
		result.put("tags", this.tags);
		result.put("episode_id", this.episode_id);
		result.put("target_url", this.target_url);
		result.put("snapshot", this.snapshot);
		result.put("snapshot1", this.snapshot1);
		result.put("snapshot2", this.snapshot2);
		result.put("convert_params",this.convert_params);
		result.put("comments", this.comments);
		return result;
	}
	public JSONObject getJSONObjectForMain(){
		/*
		 * id,title,video_catalog_id,hits,snapshot,snapshot1,snapshot2,updated_at,comments
		 */
		JSONObject result = new JSONObject();
		result.put("id", this.id);
		result.put("title", this.title);
		result.put("video_catalog_id", this.video_catalog_id);
		result.put("hits", this.hits);
		result.put("snapshot", this.snapshot);
		result.put("snapshot1", this.snapshot1);
		result.put("snapshot2", this.snapshot2);
		result.put("updated_at",this.updated_at.getTime());
		result.put("comments", this.comments);
		return result;
	}
	

}
