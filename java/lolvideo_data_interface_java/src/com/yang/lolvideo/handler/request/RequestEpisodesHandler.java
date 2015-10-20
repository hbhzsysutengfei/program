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
import com.yang.lolvideo.service.EpisodeService;
import com.yang.lolvideo.util.UtilFunctions;

public class RequestEpisodesHandler implements IRequestHandler {
	private static final String REQUEST_TYPE = LolHandler.URL_EPISODES;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ApplicationContext context;
	private Cache cache;

	public RequestEpisodesHandler(HttpServletRequest request,
			HttpServletResponse response, ApplicationContext context,
			Cache cache) {
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
			
			String cacheIndex = REQUEST_TYPE + ",start:"+start+",limit:"+limit;
			//System.out.println("cacheIndex:"+cacheIndex);
			UtilFunctions.serverLog("cacheIndex:"+cacheIndex);
			String responseData = null;
			Element element = null;
			if((element=cache.get(cacheIndex)) != null/*cache.isKeyInCache(cacheIndex)*/){
				responseData = (String) element.getValue();
				//System.out.println(REQUEST_TYPE+CACHE_DATA);
				UtilFunctions.serverLog(REQUEST_TYPE+CACHE_DATA);
			}else{
				JSONObject data = new JSONObject();
				EpisodeService episodeService = (EpisodeService) context.getBean("episodeService");
				List<Episode> records = episodeService.getEpisode(start, limit);
				
				JSONArray jRecords = new JSONArray();
				Iterator<Episode> iter = records.iterator();
				while(iter.hasNext()){
					jRecords.put(iter.next().getJSONObject());
				}
				data.put("records", jRecords);
				
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
	@Override
	public void logRequestType() {
		// TODO Auto-generated method stub
		UtilFunctions.serverLog(REQUEST_TYPE);
	}

}
