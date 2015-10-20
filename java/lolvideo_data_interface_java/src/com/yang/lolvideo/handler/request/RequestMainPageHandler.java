package com.yang.lolvideo.handler.request;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import com.yang.lolvideo.model.Announcer;
import com.yang.lolvideo.model.Bulletin;
import com.yang.lolvideo.model.Catalog;
import com.yang.lolvideo.model.Hero;
import com.yang.lolvideo.model.Video;
import com.yang.lolvideo.model.VideoType;
import com.yang.lolvideo.service.AnnouncerService;
import com.yang.lolvideo.service.BulletinService;
import com.yang.lolvideo.service.CatalogService;
import com.yang.lolvideo.service.HeroService;
import com.yang.lolvideo.service.VideoService;
import com.yang.lolvideo.service.VideoTypeService;
import com.yang.lolvideo.util.UtilFunctions;

public class RequestMainPageHandler implements IRequestHandler {
	private final static String REQUEST_TYPE = LolHandler.URL_MAIN_PAGE;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ApplicationContext context;
	private Cache cache;
	
	public RequestMainPageHandler(HttpServletRequest request,
			HttpServletResponse response, ApplicationContext context, Cache cache) {
		// TODO Auto-generated constructor stub
		this.request = request;
		this.response = response;
		this.context = context;
		this.cache = cache;
	}

	@Override
	public void doResponse() {
		// TODO Auto-generated method stub
		try{
			String clo = request.getParameter("clo");
			int cload = 20;
			if(clo != null && isNumbers(clo) ){
				cload = Integer.parseInt(clo);
			}
			String hlo = request.getParameter("hlo");
			int hload = 25;
			if(hlo != null && isNumbers(hlo)){
				hload = Integer.parseInt(hlo);
			}
			String cacheIndex = REQUEST_TYPE + ",cload:"+cload+ ",hload:"+hload;
			UtilFunctions.serverLog("cacheIndex:"+cacheIndex);
			String responseData = null;
			Element element  = null;
			if((element=cache.get(cacheIndex)) != null/*cache.isKeyInCache(cacheIndex)*/){
				responseData = (String) element.getValue();
				UtilFunctions.serverLog(REQUEST_TYPE+CACHE_DATA);
			}else{
				JSONObject data  = new JSONObject();
				BulletinService bulletinService = (BulletinService) context.getBean("bulletinService");
				HeroService heroService = (HeroService) context.getBean("heroService");
				AnnouncerService anouncerService = (AnnouncerService) context.getBean("announcerService");
				CatalogService catalogService = (CatalogService) context.getBean("catalogService");
				VideoService videoService = (VideoService) context.getBean("videoService");
				VideoTypeService videoTypeService= (VideoTypeService) context.getBean("videoTypeService");
				
				List<Bulletin> 	bulletins 	= bulletinService.getBulletins(0, 20);
				List<Hero> 		heros 		= heroService.getHeros("%", 0, hload);
				List<Announcer> announcers 	= anouncerService.getAnnouncers(0, hload);
				List<Catalog> 	catalogs 	= catalogService.getCatalogs();
				List<Video>		videos 		= new ArrayList<Video>();
				Iterator iter = catalogs.iterator();
				while(iter.hasNext()){
					videos.addAll(videoService.getCatalogVideos(((Catalog)iter.next()).getId(), "%", 0, 20));
				}
				List<VideoType> filters = videoTypeService.getAllTypes();
				
				JSONArray jBulletins = new JSONArray();
				iter = bulletins.iterator();
				while(iter.hasNext()){
					jBulletins.put(((Bulletin)iter.next()).getJSONObject());
				}
				data.put("bulletins", jBulletins);
				jBulletins = null;
				bulletins = null;
				
				JSONArray jHeros = new JSONArray();
				iter = heros.iterator();
				while(iter.hasNext()){
					jHeros.put(((Hero)iter.next()).getJSONObject());
				}
				data.put("heros", jHeros);
				jHeros=null;
				heros = null;
				
				JSONArray jAnnouncers = new JSONArray();
				iter = announcers.iterator();
				while(iter.hasNext()){
					jAnnouncers.put(((Announcer)iter.next()).getJSONObject());
				}
				data.put("announcers", jAnnouncers);
				jAnnouncers = null;
				announcers = null;
				
				JSONArray jCatalogs = new JSONArray();
				iter = catalogs.iterator();
				while(iter.hasNext()){
					jCatalogs.put(((Catalog)iter.next()).getJSONObject());
				}
				data.put("catalogs", jCatalogs);
				
				JSONArray jVideos = new JSONArray();
				iter = videos.iterator();
				while(iter.hasNext()){
					jVideos.put(((Video)iter.next()).getJSONObjectForMain());
				}
				data.put("videos", jVideos);
				jVideos = null;
				videos = null;
				
				JSONArray jFilters = new JSONArray();
				iter = filters.iterator();
				while(iter.hasNext()){
					jFilters.put(((VideoType)iter.next()).getJSONObject());
				}
				data.put("filters", jFilters);
				jFilters = null;
				filters = null;
				
				cache.put(new Element(cacheIndex,data.toString()));
				responseData = data.toString();
				//System.out.println(REQUEST_TYPE+NOT_CACHE_DATA);
				UtilFunctions.serverLog(REQUEST_TYPE+NOT_CACHE_DATA);
			}
			
			PrintWriter pw = response.getWriter();
			pw.write(responseData);
			pw.close();	
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	
	public static boolean isNumbers(String str){
		//if(str==null){ return false;}
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
