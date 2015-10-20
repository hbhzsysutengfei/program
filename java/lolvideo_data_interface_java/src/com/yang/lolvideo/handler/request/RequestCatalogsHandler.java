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
import com.yang.lolvideo.model.Catalog;
import com.yang.lolvideo.service.CatalogService;
import com.yang.lolvideo.util.UtilFunctions;

public class RequestCatalogsHandler implements IRequestHandler {
	private static final String REQUEST_TYPE = LolHandler.URL_CATALOGS;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ApplicationContext context;
	private Cache cache;
	

	public RequestCatalogsHandler(HttpServletRequest request,
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
			String cacheIndex = REQUEST_TYPE;
			UtilFunctions.serverLog("cacheIndex:"+cacheIndex);
			String responseData = null;
			Element element = null;
			if((element=cache.get(cacheIndex)) != null/*cache.isKeyInCache(cacheIndex)*/){
				responseData = (String) element.getValue();
				//System.out.println( REQUEST_TYPE+ CACHE_DATA);
				UtilFunctions.serverLog(REQUEST_TYPE+CACHE_DATA);
			}else{
				JSONObject data = new JSONObject();
				CatalogService catalogService = (CatalogService) context.getBean("catalogService");
				List<Catalog> records = catalogService.getCatalogs();
				JSONArray jRecords = new JSONArray ();
				Iterator<Catalog> iter = records.iterator();
				while(iter.hasNext()){
					jRecords.put(iter.next().getJSONObject());
				}
				data.put("catalogs", jRecords);
				responseData = data.toString();
				cache.put(new Element(cacheIndex,responseData));
				//System.out.println(REQUEST_TYPE+NOT_CACHE_DATA);
				UtilFunctions.serverLog(REQUEST_TYPE+NOT_CACHE_DATA);
			}
			PrintWriter pw = response.getWriter();
			pw.write(responseData);
			pw.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	@Override
	public void logRequestType() {
		// TODO Auto-generated method stub
		UtilFunctions.serverLog(REQUEST_TYPE);
	}

}
