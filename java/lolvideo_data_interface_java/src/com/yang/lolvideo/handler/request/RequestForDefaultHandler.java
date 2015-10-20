package com.yang.lolvideo.handler.request;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import com.yang.lolvideo.jetty.handler.LolHandler;
import com.yang.lolvideo.util.UtilFunctions;

public class RequestForDefaultHandler implements IRequestHandler {
	private final static String REQUEST_TYPE = LolHandler.URL_DEFAULT;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Cache cache;

	public RequestForDefaultHandler(HttpServletRequest request,
			HttpServletResponse response,Cache cache) {
		// TODO Auto-generated constructor stub
		this.request = request;
		this.response = response;
		this.cache = cache;
	}

	@Override
	public void doResponse() {
		// TODO Auto-generated method stub
		try {
			//System.out.println(ClassLoader.getSystemResource("lolvideodataapi.yang").getPath());
			String cacheIndex = REQUEST_TYPE;
			UtilFunctions.serverLog(cacheIndex);
			String responseData = null;
			Element element = null;
			if((element=cache.get(cacheIndex)) != null/*cache.isKeyInCache(cacheIndex)*/){
				responseData = (String) element.getValue();
				UtilFunctions.serverLog(REQUEST_TYPE+CACHE_DATA);
			}else{
				InputStream input = ClassLoader.class.getResourceAsStream("/lolvideodataapi.yang");
				InputStreamReader iReader = new InputStreamReader(input);
				BufferedReader reader = new BufferedReader (iReader);
				char [] buf = new char[1024];
				StringBuilder strBuilder = new StringBuilder();
				int len = 0;
				while((len =reader.read(buf, 0,1024)) > 0){
					strBuilder.append(buf, 0, len);
					
				}
				responseData = strBuilder.toString();
				cache.put(new Element(cacheIndex,responseData));
				UtilFunctions.serverLog(REQUEST_TYPE+NOT_CACHE_DATA);
				
				/*File file = new File(ClassLoader.getSystemResource("lolvideodataapi.yang").getPath());
				BufferedReader reader = new BufferedReader(new FileReader(file));
				//pw.write(" Welcome to Lol Video Data Interface. You can use the fixed http request get the json data, please contact hbhz.sysu.tengfei@qq.com by yang 2013.09.05");
				String line = null;
				StringBuilder str = new StringBuilder();
				while((line = reader.readLine())!= null){
					str.append(line+"\n");
				}
				responseData = str.toString();
				cache.put(new Element(cacheIndex,responseData));
				UtilFunctions.serverLog(REQUEST_TYPE+NOT_CACHE_DATA);
				*/
				
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
