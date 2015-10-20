package com.yang.lolvideo.jetty.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.Cache;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.springframework.context.ApplicationContext;

import com.yang.lolvideo.handler.request.IRequestHandler;
import com.yang.lolvideo.handler.request.InsertUserLogHandler;
import com.yang.lolvideo.handler.request.RequestAnnouncerVideoHandler;
import com.yang.lolvideo.handler.request.RequestAnnouncersHandler;
import com.yang.lolvideo.handler.request.RequestBulletinsHandler;
import com.yang.lolvideo.handler.request.RequestCatalogVideosHandler;
import com.yang.lolvideo.handler.request.RequestCatalogsHandler;
import com.yang.lolvideo.handler.request.RequestEpisodeVideosHandler;
import com.yang.lolvideo.handler.request.RequestEpisodesHandler;
import com.yang.lolvideo.handler.request.RequestFixedIdVideoHandler;
import com.yang.lolvideo.handler.request.RequestForDefaultHandler;
import com.yang.lolvideo.handler.request.RequestHeroVideosHandler;
import com.yang.lolvideo.handler.request.RequestHerosHandler;
import com.yang.lolvideo.handler.request.RequestMainPageHandler;
import com.yang.lolvideo.handler.request.RequestSearchVideos;
import com.yang.lolvideo.handler.request.RequestVersionHandler;
import com.yang.lolvideo.handler.request.RequestVideoTypesHandler;
import com.yang.lolvideo.util.UtilFunctions;

public class LolHandler extends AbstractHandler {
	public final static String URL_HEROS 			= "heros";//璇锋眰鑻遍泟闆嗗悎鐨勪俊鎭�
	public final static String URL_ANNOUNCERS 		= "announcers"; // 璇锋眰涓绘挱闆嗗悎鐨勪俊鎭�	
	public final static String URL_VERSION 			= "version"; // 璇锋眰鏈�柊鐗堟湰淇℃伅
	public final static String URL_BULLETINS		= "bulletins"; // 璇锋眰Bulletins淇℃伅
	public final static String URL_CATALOGS 		= "catalogs"; // 璇锋眰 catalogs淇℃伅
	public final static String URL_EPISODES 		= "episodes"; // 璇锋眰 episodes淇℃伅
	public final static String URL_VIDEO_TYPES		= "filters"; // 璇锋眰filters闆嗗悎淇℃伅
	
	public final static String URL_HERO_VIDEOS 		= "hero_videos"; //璇锋眰鎸囧畾鑻遍泟鐨勮棰戦泦鍚�	
	public final static String URL_ANNOUNCER_VIDEOS = "announcer_videos"; // 璇锋眰鎸囧畾涓绘挱鐨勪俊鎭泦鍚�	
	public final static String URL_EPISODE_VIDEOS 	= "episode_videos"; // 璇锋眰闆嗛敠鐨勮棰戦泦鍚堜俊鎭�	
	public final static String URL_CATALOG_VIDEOS 	= "catalog_videos"; // 璇锋眰瑙嗛鏍忕洰闆嗗悎淇℃伅
	public final static String URL_VIDEOS			= "videos"; 		//瑙嗛 videos 娌℃湁鍚庣画鍙傛暟锛屽垯涓嶈繑鍥�	
	public final static String URL_VIDEO_QUERY		= "q";				// 瑙ｆ瀽/videos/q 璺緞 锛�鍓嶉潰蹇呴』鏄痸ideos 鐒跺悗鍦ㄥ垽鏂�q 
	public final static String URL_VIDEO_FIXED_ID	= "video_fixed_id"; // /videos/[int video_id]
	
	public final static String URL_MAIN_PAGE		= "main_page"; // 鍏煎android 瀹㈡埛绔殑娉ㄨВ闈㈢▼搴�璇锋眰url 锛�videos?mac=%s&v="+VERSION_NAME+"&clo=%d&hlo=%d
	
	public final static String URL_DEFAULT			= "default_page";
	
	
	
	private ApplicationContext context;
	private Cache cache;
	
	public LolHandler(ApplicationContext context, Cache cache){
		this.context = context;
		this.cache = cache;
	}
	public void setApplicationContext(ApplicationContext context){
		this.context = context;
	}
	public void setCache(Cache cache){
		this.cache = cache;
	}
	
	@Override
	public void handle(String target, Request baseRequest, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/plain;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		baseRequest.setHandled(true);
		if(target.equalsIgnoreCase("/favicon.ico")){
			return;
		}
		//add the request to database user_statistic
		this.logRequest(request);
		
		UtilFunctions.serverLog(request.getRequestURL().append("?"+request.getQueryString()).toString());
		//杞氦缁檋andleRequest鍑芥暟
		handleRequest(target,request, response);
	}
	
	private void handleRequest(String target, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub		
		String paths[] = target.split("/");
		String requestType = this.getRequestType(paths);		
		switch(requestType){
			case URL_HEROS: // /heros?a=1&b=2...
				handleRequestHeros(request,response);
				break;
			case URL_ANNOUNCERS: // /announcers?a=1&b=2
				handleRequestAnnouncers(request, response);
				break;
			case URL_VERSION: // /version?p=android|| ios
				handleRequestVersion(request,response);
				break;
			case URL_BULLETINS: // /bulletins
				handleRequestBulletins(request,response);
				break;
			case URL_CATALOGS: // /catalogs/[int catalog_id]
				handleRequestCatalogs(request,response);
				break;
			case URL_EPISODES:// /episodes/[int episode_id]
				handleRequestEpisode(request, response);
				break;
			case URL_HERO_VIDEOS:// /heros/[int hero_id]
				handleRequestHeroVideos(request, response,paths[2]);
				break;
			case URL_ANNOUNCER_VIDEOS: // /anouncers/[int announcer_id]
				handleRequestAnnouncerVideos(request,response,paths[2]);
				break;
			case URL_EPISODE_VIDEOS:// /episodes/[int episode_id]
				handleRequestEpisodeVideos(request,response,paths[2]);
				break;
			case URL_CATALOG_VIDEOS:// /catalogs/[int catalog_id]
				handleRequestCatalogVideos(request,response,paths[2]);
				break;
			case URL_VIDEO_FIXED_ID: // /videos/[int video_id]
				handleRequestFixedIdVieo(request,response,paths[2]);
				break;
			case URL_VIDEO_QUERY: // /videos/q?
				handleRequestSearchVideos(request,response);
				break;
			case URL_MAIN_PAGE: // /videos?mac=[string]&v=[string]&p=[string]&clo=[int]&hlo=[int]
				handleRequestMainPage(request,response);
				break;
			case URL_VIDEO_TYPES:
				handleRequestVideoTypes(request,response);
				break;
				
			default:
				handleRequestForDefault(request,response);					
		}
	}
	
	
	/*
	 *  judge the request type 
	 */
	private String getRequestType(String [] paths){
		String result = "";
		if(paths.length==2 && paths[1].equalsIgnoreCase(URL_HEROS)){
			result = URL_HEROS; 
		}else if(paths.length == 2 && paths[1].equalsIgnoreCase(URL_ANNOUNCERS)){
			result = URL_ANNOUNCERS;
		}else if(paths.length==2 && paths[1].equalsIgnoreCase(URL_VERSION)){
			result = URL_VERSION;
		}else if(paths.length==2 && paths[1].equalsIgnoreCase(URL_BULLETINS)){
			result = URL_BULLETINS;
		}else if(paths.length==2 && paths[1].equalsIgnoreCase(URL_CATALOGS)){
			result = URL_CATALOGS;
		}else if(paths.length==2 && paths[1].equalsIgnoreCase(URL_EPISODES)){
			result = URL_EPISODES;
		}else if(paths.length==3 && paths[1].equalsIgnoreCase(URL_HEROS) && isNumbers(paths[2])){
			result= URL_HERO_VIDEOS;
		}else if(paths.length==3 && paths[1].equalsIgnoreCase(URL_ANNOUNCERS) && isNumbers(paths[2])){
			result = URL_ANNOUNCER_VIDEOS;
		}else if(paths.length==3 && paths[1].equalsIgnoreCase(URL_EPISODES) && isNumbers(paths[2])){
			result = URL_EPISODE_VIDEOS;
		}else if(paths.length==3 && paths[1].equalsIgnoreCase(URL_CATALOGS) && isNumbers(paths[2])){
			result = URL_CATALOG_VIDEOS;
		}else if(paths.length==3 && paths[1].equalsIgnoreCase(URL_VIDEOS)&& isNumbers(paths[2]) ){
			result = URL_VIDEO_FIXED_ID;
		}else if(paths.length==3 && paths[1].equalsIgnoreCase(URL_VIDEOS)&& paths[2].equalsIgnoreCase(URL_VIDEO_QUERY)){
			result = URL_VIDEO_QUERY;
		}else if(paths.length==2 && paths[1].equalsIgnoreCase(URL_VIDEOS)){
			result = URL_MAIN_PAGE;
		}else if(paths.length==2 && paths[1].equalsIgnoreCase(URL_VIDEO_TYPES)){
			result = URL_VIDEO_TYPES;
		}
		return result;	
	}
	
	public static boolean isNumbers(String str){
		if(str==null){ return false;}
		for(int i= 0; i< str.length();i++){
			if(!Character.isDigit(str.charAt(i))){
				return false;
			}
		}
		return true;
	}
	//灏嗚闂殑娑堟伅娣诲姞鍒版暟鎹簱 user_statistic
	private void logRequest(HttpServletRequest request){
		(new InsertUserLogHandler(request,context)).execute();
	}
	/*
	 * handle request heros set
	 */
	private void handleRequestHeros(HttpServletRequest request, HttpServletResponse response){
		IRequestHandler handler = new RequestHerosHandler(request,response,context,cache);
		handler.logRequestType();
		handler.doResponse();
	}
	/*
	 * handle request announcers set
	 */
	private void handleRequestAnnouncers(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		IRequestHandler handler = new RequestAnnouncersHandler(request, response, context, cache);
		handler.logRequestType();
		handler.doResponse();		
	}
	/*
	 * get the latest version for the given platform
	 */
	private void handleRequestVersion(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		IRequestHandler handler = new  RequestVersionHandler(request,response,context,cache);
		handler.logRequestType();
		handler.doResponse();
	}
	/*
	 * get the bulletins info set 
	 */
	private void handleRequestBulletins(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		IRequestHandler handler = new RequestBulletinsHandler(request, response,context,cache); 
		handler.logRequestType();
		handler.doResponse();
	}
	/*
	 * get ths catalogs info set 
	 */
	private void handleRequestCatalogs(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		IRequestHandler handler = new RequestCatalogsHandler(request, response, context, cache);
		handler.logRequestType();
		handler.doResponse();
	}
	/*
	 * get the episodes info set
	 */
	private void handleRequestEpisode(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		IRequestHandler handler = new RequestEpisodesHandler(request, response,context,cache);
		handler.logRequestType();
		handler.doResponse();
	}
	/*
	 * get video set of the given hero  
	 */
	private void handleRequestHeroVideos(HttpServletRequest request,
			HttpServletResponse response, String heros) {
		// TODO Auto-generated method stub
		IRequestHandler handler = new RequestHeroVideosHandler( request, response, context,cache,heros);
		handler.logRequestType();
		handler.doResponse();
	}
	/*
	 * get video set of the given announcer 
	 */
	private void handleRequestAnnouncerVideos(HttpServletRequest request,
			HttpServletResponse response, String announcers) {
		// TODO Auto-generated method stub
		IRequestHandler handler = new RequestAnnouncerVideoHandler(request,response,context,cache, announcers);
		handler.logRequestType();
		handler.doResponse();
	}
	/*
	 * get video set of the given episode
	 */
	private void handleRequestEpisodeVideos(HttpServletRequest request,
			HttpServletResponse response, String episode_id) {
		// TODO Auto-generated method stub
		IRequestHandler handler = new RequestEpisodeVideosHandler(request,response,context,cache,episode_id);
		handler.logRequestType();
		handler.doResponse();
	}
	/*
	 * get video set of the given catalog
	 */
	private void handleRequestCatalogVideos(HttpServletRequest request,
			HttpServletResponse response, String catalog_id) {
		// TODO Auto-generated method stub
		IRequestHandler handler = new RequestCatalogVideosHandler(request,response,context,cache,catalog_id);
		handler.doResponse();
	}
	/*
	 * default request response 
	 */
	private void handleRequestForDefault(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		IRequestHandler handler = new RequestForDefaultHandler(request,response,cache);
		handler.logRequestType();
		handler.doResponse();
	}
	/*
	 * get the fixed id video unit info
	 */
	private void handleRequestFixedIdVieo(HttpServletRequest request,
			HttpServletResponse response, String video_id) {
		// TODO Auto-generated method stub
		IRequestHandler handler = new RequestFixedIdVideoHandler(request,response,context,cache,video_id);
		handler.logRequestType();
		handler.doResponse();
	}
	/*
	 * get video set by the given conditions 
	 */
	private void handleRequestSearchVideos(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		IRequestHandler handler = new RequestSearchVideos(request, response,context,cache);
		handler.logRequestType();
		handler.doResponse();
	}
	/*
	 * get the main page info 
	 */
	private void handleRequestMainPage(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		IRequestHandler handler = new RequestMainPageHandler(request, response,context,cache);
		handler.logRequestType();
		handler.doResponse();
	}
	/*
	 * get the video types set
	 */
	private void handleRequestVideoTypes(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		IRequestHandler handler = new RequestVideoTypesHandler(request,response,context,cache);
		handler.doResponse();
	}

}
