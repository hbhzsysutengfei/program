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
import com.yang.lolvideo.model.Video;
import com.yang.lolvideo.service.VideoService;
import com.yang.lolvideo.util.UtilFunctions;

public class RequestCatalogVideosHandler implements IRequestHandler {
	private final static String REQUEST_TYPE = LolHandler.URL_CATALOG_VIDEOS;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ApplicationContext context;
	private Cache cache;
	private String catalog_id;

	public RequestCatalogVideosHandler(HttpServletRequest request,
			HttpServletResponse response, ApplicationContext context,
			Cache cache, String catalog_id) {
		// TODO Auto-generated constructor stub
		this.request = request;
		this.response = response;
		this.context = context;
		this.cache = cache;
		this.catalog_id = catalog_id;
	}

	@Override
	public void doResponse() {
		// TODO Auto-generated method stub
		try {
			String index = request.getParameter("i");
			int start = 0;
			try{start = Integer.parseInt(index);
			}catch(Exception e){
				start=0;
			}
			String lo = request.getParameter("lo");
			int limit = 20;
			try{
				limit = Integer.parseInt(lo);
			}catch(Exception e){
				limit = 20;
			}
			String video_types = request.getParameter("t");
			
			if ( video_types==null ||!isNumbers(video_types)){
				video_types="%";
			}else{
				video_types = "%,"+video_types+",%";
			}
			
			String cacheIndex = REQUEST_TYPE + ",catalog_id:"+this.catalog_id +
					"video_types:"+video_types +",start:"+start + ",limit:" + limit;
			UtilFunctions.serverLog("cacheIndex:"+cacheIndex);
			String responseData = null;
			Element element = null;
			if((element=cache.get(cacheIndex)) != null/*cache.isKeyInCache(cacheIndex)*/){
				responseData = (String) element.getValue();
				//System.out.println(REQUEST_TYPE + CACHE_DATA);
				UtilFunctions.serverLog(REQUEST_TYPE+CACHE_DATA);
			}else{
				JSONObject data  = new JSONObject();
				VideoService videoService = (VideoService) context.getBean("videoService");
				int count = videoService.getCatalogVideoCount(Integer.parseInt(catalog_id), video_types);
				List<Video> records = videoService.getCatalogVideos(Integer.parseInt(catalog_id), video_types, start, limit);
				
				data.put("count", count);
				JSONArray jRecords = new JSONArray();
				Iterator<Video> iter = records.iterator();
				while(iter.hasNext()){
					jRecords.put(iter.next().getJSONObject());
				}
				data.put("videos",jRecords);
				
				cache.put(new Element(cacheIndex,data.toString()));
				responseData = data.toString();
				//System.out.println(REQUEST_TYPE+NOT_CACHE_DATA);
				UtilFunctions.serverLog(REQUEST_TYPE+NOT_CACHE_DATA);
			}
			
			PrintWriter pw = response.getWriter();
			pw.write(responseData);
			pw.close();	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static boolean isNumbers(String str){
		if(str == null) return false;
		for(int i= 0; i< str.length();i++){
			if(!Character.isDigit(str.charAt(i))){
				return false;
			}
		}
		return true;
	}
	@Override
	public void logRequestType() {
		// TODO Auto-generated method stub
		UtilFunctions.serverLog(REQUEST_TYPE);
	}
}
