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
import com.yang.lolvideo.model.Bulletin;
import com.yang.lolvideo.model.Hero;
import com.yang.lolvideo.model.VideoType;
import com.yang.lolvideo.service.BulletinService;
import com.yang.lolvideo.util.UtilFunctions;

public class RequestBulletinsHandler implements IRequestHandler {
	private final static String REQUEST_TYPE = LolHandler.URL_BULLETINS;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ApplicationContext context;
	private Cache cache;
	
	
	public RequestBulletinsHandler(HttpServletRequest request,
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
		try {
			String index = request.getParameter("i");
			int start = 0;
			try{start = Integer.parseInt(index);
			}catch(Exception e){
				start=0;
			}
			String lo = request.getParameter("lo");
			int limit = 25;
			try{
				limit = Integer.parseInt(lo);
			}catch(Exception e){
				limit = 25;
			}
			
			String cacheIndex = REQUEST_TYPE + ",start:"+start+",limit:"+limit;
			UtilFunctions.serverLog("cacheIndex:"+cacheIndex);
			//System.out.println("cacheIndex:"+cacheIndex);
			
			String responseData = null;
			Element element = null;
			if((element=cache.get(cacheIndex)) != null/*cache.isKeyInCache(cacheIndex)*/){
				responseData =  (String) element.getValue();
				//System.out.println(REQUEST_TYPE+CACHE_DATA);
				UtilFunctions.serverLog(REQUEST_TYPE+CACHE_DATA);
			}else{
				JSONObject data = new JSONObject();
				BulletinService bulletinService = (BulletinService) context.getBean("bulletinService");
				List<Bulletin> records =bulletinService.getBulletins(start, limit);				
				JSONArray jRecords = new JSONArray();
				Iterator<Bulletin> iter = records.iterator();
				while(iter.hasNext()){
					jRecords.put(iter.next().getJSONObject());
				}
				data.put("bulletins", jRecords);	
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
