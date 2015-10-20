package com.yang.lolvideo.handler.request;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.springframework.context.ApplicationContext;

import com.yang.lolvideo.jetty.handler.LolHandler;
import com.yang.lolvideo.model.Version;
import com.yang.lolvideo.service.VersionService;
import com.yang.lolvideo.util.UtilFunctions;

public class RequestVersionHandler implements IRequestHandler {
	private final static String REQUEST_TYPE = LolHandler.URL_VERSION;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ApplicationContext context;
	private Cache cache;
	
	public RequestVersionHandler(HttpServletRequest request,
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
			String platform = request.getParameter("p");
			platform = platform==null?"android":platform;
			String cacheIndex = REQUEST_TYPE+",p:"+platform;
			UtilFunctions.serverLog("cacheIndex:"+cacheIndex);
			String responseData = null;
			Element element = null;
			if((element=cache.get(cacheIndex)) != null/*cache.isKeyInCache(cacheIndex)*/){
				responseData = (String) element.getValue();
				//System.out.println(REQUEST_TYPE+":缓存数据");
				UtilFunctions.serverLog(REQUEST_TYPE+CACHE_DATA);
			}else{
				VersionService versionService = (VersionService) context.getBean("versionService");
				Version version = versionService.getLatestVersion(platform==null?VersionService.PLATFORM_ANDROID:platform);
				if(version==null){
					UtilFunctions.serverLog(REQUEST_TYPE+"version is null");
					return;
				}
				responseData = version.getJSONObject().toString();
				cache.put(new Element(cacheIndex,responseData));
				//System.out.println(REQUEST_TYPE+":非缓存数据");
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
