package com.yang.lolvideo.handler.request;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;

import com.yang.lolvideo.jetty.handler.LolHandler;
import com.yang.lolvideo.model.Episode;
import com.yang.lolvideo.model.Video;
import com.yang.lolvideo.service.EpisodeService;
import com.yang.lolvideo.service.VideoService;
import com.yang.lolvideo.util.UtilFunctions;

public class RequestFixedIdVideoHandler implements IRequestHandler {
	private final static String REQUEST_TYPE = LolHandler.URL_VIDEO_FIXED_ID;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ApplicationContext context;
	private Cache cache;
	private int video_id;

	public RequestFixedIdVideoHandler(HttpServletRequest request,
			HttpServletResponse response, ApplicationContext context,
			Cache cache, String video_id) {
		// TODO Auto-generated constructor stub
		this.request = request;
		this.response = response;
		this.context = context;
		this.cache = cache;
		this.video_id = Integer.parseInt(video_id);
	}

	@Override
	public void doResponse() {
		// TODO Auto-generated method stub
		try{
			String cacheIndex = REQUEST_TYPE + ",id:"+this.video_id;
			UtilFunctions.serverLog("cacheIndex:"+cacheIndex);
			String responseData = null;
			Element element = null;
			if((element=cache.get(cacheIndex)) != null/*cache.isKeyInCache(cacheIndex)*/){
				responseData = (String) element.getValue();
				//System.out.println(REQUEST_TYPE+CACHE_DATA);
				UtilFunctions.serverLog(REQUEST_TYPE+CACHE_DATA);
			}else{
				JSONObject data  = new JSONObject();
				VideoService videoService = (VideoService) context.getBean("videoService");
				Video video = videoService.getVideoById(video_id);
				if(video==null){
					UtilFunctions.serverLog(REQUEST_TYPE+"video is null");
					return;
				}
				JSONObject jVideo = video.getJSONObjectForID();
				
				/* 添加和该视频相关的视频 集合 保存到jVideo  的episodes  字段 其中有  id , title, records 
				 * 	id 中 0 表示空的集锦，所以，需要根据该指定视频的类型去寻找相关视频 未完成
				 */
				/*int episode_id = video.getEpisode_id();
				
			
				String announcers = video.getAnouncers();
				String tags = video.getTags();
				List<Video> relativeVideos = null;
				
				if (episode_id != 0){// 有集锦
					relativeVideos = videoService.getEpisodeVideos(episode_id, 0, 20);
				}else if(!announcers.isEmpty()){// 没有集锦 有主播
					announcers ="%,"+ announcers.split(",")[1]+",%";
					relativeVideos = videoService.getAnnouncerVideos(announcers, 0, 20);
				}else if(!tags.isEmpty()){// tags 分 
					System.out.println("tags: " + tags);
					relativeVideos = videoService.getVideosByTags(tags, 0, 20);
				}else { // episode_id = 0 or 2
					relativeVideos = videoService.getEpisodeVideos(episode_id, 0, 20);
				}
				EpisodeService episodeService = (EpisodeService) context.getBean("episodeService");
				//System.out.println("Episode id is " + episode_id);
				Episode episode = episodeService.getEpisodeByID(episode_id);
				JSONObject jEpisode = new JSONObject();
				jEpisode.put("id", episode_id == 0 ? 2:episode_id);
				jEpisode.put("title", episode.getTitle()==null?"相关视频":episode.getTitle());
				
				JSONArray records = new  JSONArray();
				Iterator<Video> iter = relativeVideos.iterator();
				while(iter.hasNext()){
					records.put(iter.next().getJSONObject());
				}
				jEpisode.put("records", records);
				jVideo.put("episode", jEpisode);
				*/
				data.put("video", jVideo);
				responseData = data.toString();
				cache.put(new Element(cacheIndex,responseData));
				UtilFunctions.serverLog(REQUEST_TYPE+NOT_CACHE_DATA);
			}
			PrintWriter pw = response.getWriter();
			pw.write(responseData);
			pw.close();	
		}catch(IOException e){
			
		}
		
	}
	@Override
	public void logRequestType() {
		// TODO Auto-generated method stub
		UtilFunctions.serverLog(REQUEST_TYPE);
	}

}
