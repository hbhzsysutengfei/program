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
import com.yang.lolvideo.model.VideoType;
import com.yang.lolvideo.service.VideoTypeService;
import com.yang.lolvideo.util.UtilFunctions;

public class RequestVideoTypesHandler implements IRequestHandler {
	private final static String REQUEST_TYPE = LolHandler.URL_VIDEO_TYPES;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ApplicationContext context;
	private Cache cache;

	public RequestVideoTypesHandler(HttpServletRequest request,
			HttpServletResponse response, ApplicationContext context,
			Cache cache) {
		// TODO Auto-generated constructor stub
		this.request = request;
		this.response= response;
		this.context = context;
		this.cache =cache;
	}

	@Override
	public void doResponse() {
		// TODO Auto-generated method stub
		try{
			String t = request.getParameter("t");
			String cacheIndex = REQUEST_TYPE + ",type:"+ t==null?"all":t;
			String responseData = null;
			Element element = null;
			if((element=cache.get(cacheIndex)) != null/*cache.isKeyInCache(cacheIndex)*/){
				responseData = (String) element.getValue();
				UtilFunctions.serverLog(REQUEST_TYPE+CACHE_DATA);
			}else{
				JSONObject data = new JSONObject();
				VideoTypeService videoTypeService= (VideoTypeService) context.getBean("videoTypeService");
				List<VideoType> filters =null;
				if(t==null){
					filters = videoTypeService.getAllTypes();
				}else if(t.equalsIgnoreCase("heros")){
					filters = videoTypeService.getAllHeroTypes();
				}else if(t.equalsIgnoreCase("video")){
					filters = videoTypeService.getAllVideoTypes();
				}
				
				Iterator<VideoType> iter1 = filters.iterator();
				JSONArray jFilter = new JSONArray();
				while(iter1.hasNext()){
					jFilter.put(iter1.next().getJSONObject());
				}
				data.put("filters", jFilter);
				cache.put(new Element(cacheIndex,data.toString()));
				responseData = data.toString();
				//System.out.println("非缓存数据");
				UtilFunctions.serverLog(REQUEST_TYPE+NOT_CACHE_DATA);
			}
			
			
			
			PrintWriter pw = response.getWriter();
			pw.write(responseData);
			pw.close();		
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}

	@Override
	public void logRequestType() {
		// TODO Auto-generated method stub
		UtilFunctions.serverLog(REQUEST_TYPE);
	}

}
