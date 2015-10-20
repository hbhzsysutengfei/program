package com.yang.lolvideo.handler.request;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
import com.yang.lolvideo.model.Episode;
import com.yang.lolvideo.model.Hero;
import com.yang.lolvideo.model.UserKeyword;
import com.yang.lolvideo.model.Video;
import com.yang.lolvideo.model.VideoType;
import com.yang.lolvideo.service.AnnouncerService;
import com.yang.lolvideo.service.EpisodeService;
import com.yang.lolvideo.service.HeroService;
import com.yang.lolvideo.service.UserKeywordService;
import com.yang.lolvideo.service.VideoService;
import com.yang.lolvideo.service.VideoTypeService;
import com.yang.lolvideo.util.UtilFunctions;

public class RequestSearchVideos implements IRequestHandler {
	private final static String REQUEST_TYPE = LolHandler.URL_VIDEO_QUERY;
	private final static String RELATIVE_VIDEOS = "relative_videos";
	private final static String RELATIVE_TYPE = "relative_type";
	private final static String RELATIVE_TYPE_EPISODE = "episode";
	private final static String RELATIVE_TYPE_ANNOUNCERS = "announcers";
	private final static String RELATIVE_TYPE_TAGS = "tags";
	private final static String RELATIVE_TYPE_OTHERS = "others";
	
	private final static String SEARCH_VIDEO_BY_KEYWORDS = "search_video_by_keywords";
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ApplicationContext context;
	private Cache cache;
	
	public RequestSearchVideos(HttpServletRequest request,
			HttpServletResponse response, ApplicationContext context,
			Cache cache) {
		// TODO Auto-generated constructor stub
		this.request = request;
		this.response = response;
		this.context = context;
		this.cache =cache;
	}
	/*
	 * (non-Javadoc)
	 * @see com.yang.lolvideo.handler.request.IRequestHandler#doResponse()
	 * 		@Param("title")				String 		title,// like		=> k	null
			@Param("heros")				String 		heros,// like		=> h	null
			@Param("announcers")		String 		announcers,// like  => a	null
			@Param("video_catalog_id") 	int 		video_catalog_id,	=> c	null
			@Param("video_types")		String		video_types, // like =>t	null
			@Param("episode_id")		int			episode_id, // = 	=> e	null
			@Param("start")				int 		start, 				=> i	default 0
			@Param("limit") 			int 		limit				=> lo	default 20
			
			r => do not include 
	 */
	
	public static boolean isNumbers(String str){
		//if(str == null) return false;
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
	@Override
	public void doResponse() {
		// TODO Auto-generated method stub
		
		String k = request.getParameter("k");
		String r = request.getParameter("r");
		//System.out.println("k:" + k);
		if(k!=null || r != null ){
			/*  
			 * 鏈夊叧閿瓧 鍙兘鏄富鎾紝 鍙兘鏄�鑻遍泟锛�鍙兘鏄棰戦泦閿﹀悕绉帮紝 涔熷彲鑳芥槸瑙嗛鐨則ags锛�鎴栬� title
			 * 鍙兘鐢ㄥ埌杩欓噷鐨勬槸 瑙嗛鐨勬悳绱�锛�videos/q?k=key1 key2 
			 * 涔熷彲鑳芥槸鐩稿叧瑙嗛锛岀浉鍏宠棰戠殑璇濓紝/videos/q?k=tags&r=video_id&lo=12
			 */
			
			if(r != null && isNumbers(r)){
				//鎼滅储鍜岃瑙嗛鐩稿叧鐨勮棰戦泦鍚�				this.getRelativeVideosByVideoId(request, response );
			}
			else {
				this.getVideosByKeywords(request, response);
				//String 
				
				// 鍏抽敭瀛楁悳绱�				
				/*String[] keywords = k.split(" ");
				for(int i = 0 ; i< keywords.length; i++){
					System.out.println(keywords[i]);
				}
				this.getVideosByKeywords(request, response);
				*/
			}
			
			
			
			
		}
		else{
			/*瑙嗛鏌ユ壘
			 * heros h int to string like 
			 *  announcers a int to string like 
			 *  video_catalog_id c int to int =
			 *  video_types t int to string like
			 *  episode_id	e int to int =
			 *  start		i int to int =
			 *  limit 		lo int to int = 
			 *  
			 */
			try {
				String heros = request.getParameter("h");
				if(heros!=null && isNumbers(heros)){
					heros = "%,"+heros+",%";
				}
				String announcers = request.getParameter("announcers");
				if(announcers != null && isNumbers(announcers)){
					announcers = "%,"+announcers+",%";
				}
				String c = request.getParameter("c");
				int video_catalog_id = -1;
				if(c!=null && isNumbers(c)){
					video_catalog_id = Integer.parseInt(c);
				}
				String video_types = request.getParameter("t");
				if(video_types!=null && isNumbers(video_types)){
					video_types = "%,"+video_types+",%";
				}
				String e = request.getParameter("e");
				int episode_id = -1;
				if(e != null && isNumbers(e)){
					episode_id  = Integer.parseInt(e);
				}			
				
				String index = request.getParameter("i");
				int start = 0;
				if(index != null && isNumbers(index)){
					start = Integer.parseInt(index);
				}
				String lo = request.getParameter("lo");
				int limit = 20;
				if(lo != null&& isNumbers(lo)){
					limit=Integer.parseInt(lo);
				}
				
				String cacheIndex = REQUEST_TYPE + 
						",heros:"+heros+
						",announcers:"+announcers+
						",video_catalog_id:"+video_catalog_id+
						",video_types:"+video_types+
						",episode_id:"+episode_id+
						",start:"+start+
						",limit"+limit;
				UtilFunctions.serverLog("cacheIndex:"+cacheIndex);
				String responseData = null;
				Element element = null;
				if((element=cache.get(cacheIndex))!= null){
					responseData = (String) element.getValue();
					UtilFunctions.serverLog(REQUEST_TYPE+CACHE_DATA);
				}else{
					JSONObject data  = new JSONObject();
					VideoService videoService = (VideoService) context.getBean("videoService");
					int count = videoService.getVideoCount(null, heros, announcers, video_catalog_id, video_types, episode_id);
					List<Video> records = videoService.getVideos(null, heros, announcers, video_catalog_id, video_types, episode_id, start, limit);
					data.put("count", count);
					JSONArray jRecords = new JSONArray();
					Iterator<Video> iter = records.iterator();
					while(iter.hasNext()){
						jRecords.put(iter.next().getJSONObject());
					}
					data.put("videos", jRecords);
					responseData = data.toString();
					cache.put(new Element(cacheIndex,responseData));
					
					//System.out.println(REQUEST_TYPE+NOT_CACHE_DATA);	
					UtilFunctions.serverLog(REQUEST_TYPE+NOT_CACHE_DATA);
				}
			
				PrintWriter pw= response.getWriter();
				pw.write(responseData);
				pw.close();	
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
	}
	/*
	 *  鏍规嵁瑙嗛棰滻D 鎼滅储鐩稿叧鐨勮棰戯紝 鍋氱紦瀛�	 *  episode_id => announcers => tags => others 
	 *  1.鎵惧埌璇ヨ棰戠殑淇℃伅
	 */
	private void  getRelativeVideosByVideoId(HttpServletRequest request, HttpServletResponse response){
		try{
			int r = Integer.parseInt(request.getParameter("r"));
			String index = request.getParameter("i");
			int start = 0;
			if(index != null && isNumbers(index)){
				start = Integer.parseInt(index);
			}
			String lo = request.getParameter("lo");
			int limit = 12;
			if(lo != null&& isNumbers(lo)){
				limit=Integer.parseInt(lo);
			}
			
			String cacheIndex = REQUEST_TYPE +","+ RELATIVE_VIDEOS + ",video_id" + r + ",start:"+ start+ ",limit:"+limit;
			UtilFunctions.serverLog("cacheIndex:"+cacheIndex);
			
			String responseData = null;
			Element element = null;
			if((element = cache.get(cacheIndex) )!= null){
				responseData = (String) element.getValue();
				UtilFunctions.serverLog(REQUEST_TYPE+",video id"+r+","+RELATIVE_TYPE+","+CACHE_DATA);	
			}else{
				VideoService videoService = (VideoService) context.getBean("videoService");
				Video video = videoService.getVideoById(r);
				if(video == null){
					UtilFunctions.serverLog("瑙嗛淇℃伅涓嶅瓨鍦� video id" + r );
					return;
				}
				
				List<Video> relativeVideos  = null;
				int count = 0;
				int episode_id = video.getEpisode_id();
				String announcers = video.getAnouncers();
				if(announcers != null && (!announcers.isEmpty())){
					announcers = "%,"+announcers.split(",")[1]+",%";
				}
				String tags = video.getTags().trim();	
				
				if(episode_id > 0){//  鏈夐泦閿�					//System.out.println("闆嗛敠鐩稿叧");
					UtilFunctions.serverLog(REQUEST_TYPE+",video "+r+" "+RELATIVE_TYPE+":"+RELATIVE_TYPE_EPISODE+",episode id :" + episode_id);
					count = videoService.getEpisodeVideoCount(episode_id);
					relativeVideos =  videoService.getEpisodeVideos(episode_id, start,limit);
				}else if((announcers!= null )&& (!announcers.isEmpty())){ // 娌℃湁闆嗛敠鏈変富鎾�					//System.out.println("涓绘挱鐩稿叧");
					UtilFunctions.serverLog(REQUEST_TYPE+",video "+r+" "+RELATIVE_TYPE+":"+RELATIVE_TYPE_ANNOUNCERS+",announcer_id"+ announcers);
					count = videoService.getAnnouncerVideoCount(announcers);
					relativeVideos = videoService.getAnnouncerVideos(announcers, start, limit);
				}else if(tags != null){// 娌℃湁闆嗛敠,娌℃湁涓绘挱锛屾湁tags
					//System.out.println("tags 鐩稿叧");
					UtilFunctions.serverLog(REQUEST_TYPE+",video "+r+" "+RELATIVE_TYPE+":"+RELATIVE_TYPE_TAGS+",tags:"+tags);
					count = videoService.getVideoCountByTags(tags);
					//System.out.println("tags:" + tags +":end");
					//System.out.println("count:" + count);
					relativeVideos = videoService.getVideosByTags(tags, start, limit);
				}else  { // 闅忎究鎵句簡
					//System.out.println("鏃犵浉鍏�);
					UtilFunctions.serverLog(REQUEST_TYPE+",video "+r+" "+RELATIVE_TYPE+":"+RELATIVE_TYPE_OTHERS);
					count = videoService.getVideoCount(null, null, null, -1, null, -1);
					relativeVideos = videoService.getVideos(null, null, null, -1, null, -1, start, limit);
				}
				
				JSONObject data = new JSONObject();
				data.put("count", count);
				
				Iterator<Video> iter = relativeVideos.iterator();
				JSONArray jRecords = new JSONArray();
				
				while(iter.hasNext()){
					jRecords.put(iter.next().getJSONObject());
				}
				data.put("videos", jRecords);
				responseData = data.toString();
				cache.put(new Element(cacheIndex,responseData));
				//System.out.println(REQUEST_TYPE+NOT_CACHE_DATA);	
				UtilFunctions.serverLog(REQUEST_TYPE+",video id"+r+","+RELATIVE_TYPE+","+NOT_CACHE_DATA);
			}
			
			PrintWriter pw= response.getWriter();
			pw.write(responseData);
			pw.close();	
		}catch(IOException ex){
			ex.printStackTrace();
		}	
	}
	/*
	 * 鏍规嵁鍏抽敭瀛楁煡鎵捐棰戝苟涓旇繑鍥炴暟鎹�鍋氱紦瀛�	 */
	private void getVideosByKeywords(HttpServletRequest request, HttpServletResponse response){
		//System.out.println("key search ");
		try{
			//get parameters k, i, lo
			String[] keywords = request.getParameterValues("k");
			String index = request.getParameter("i");
			int start = 0;
			if(index != null && isNumbers(index)){
				start = Integer.parseInt(index);
			}
			String lo = request.getParameter("lo");
			int limit = 20;
			if(lo != null&& isNumbers(lo)){
				limit=Integer.parseInt(lo);
			}
			
			//generate cacheIndex
			StringBuilder strBuilder = new StringBuilder();
			for(String key : keywords){
				strBuilder.append(key).append(",");
			}
			String cacheIndex = REQUEST_TYPE +"," + SEARCH_VIDEO_BY_KEYWORDS + ":"+ strBuilder + ",start:"+start+",limit:"+ limit;
			UtilFunctions.serverLog("cacheIndex:" + cacheIndex);
			
			//log user search keywords
			this.logUserKeywords(strBuilder.toString());
			
			String responseData = null;
			Element element = null;
			if((element= cache.get(cacheIndex)) != null){
				responseData = (String) element.getValue();
				UtilFunctions.serverLog(REQUEST_TYPE+ CACHE_DATA + "keywords:" + strBuilder);
			}else{
				String heros = null;
				String announcers = null;
				int catalog_id = -1;
				int episode_id = -1;
				String video_types = null;
				HeroService 		heroService 		= (HeroService) 		context.getBean("heroService");
				AnnouncerService 	announcerService 	= (AnnouncerService) 	context.getBean("announcerService");
				EpisodeService 		episodeService		= (EpisodeService) 		context.getBean("episodeService");
				VideoTypeService	videoTypeService	= (VideoTypeService)	context.getBean("videoTypeService");
				
				
				//澶勭悊鍏抽敭瀛�				
				for (int i = 0 ; i < keywords.length; i++){
					//heros
					if(heros == null ){
						Hero hero = heroService.getHeroByName("%"+keywords[i]+"%");
						if (hero != null ){
							heros = "%,"+ hero.getId() + ",%";
							UtilFunctions.serverLog("Keywords '"+keywords[i]+"' 鎴愬姛鍖归厤鑻遍泟: '"+ hero.getNick()+hero.getName()+"'");
							continue;
						}
					}
					//announcers
					if(announcers == null){
						Announcer announcer = announcerService.getAnouncerByName("%"+keywords[i]+"%");
						if(announcer != null){
							announcers = "%," + announcer.getId() + ",%";
							UtilFunctions.serverLog("keywords '"+keywords[i] +"' 鎴愬姛鍖归厤涓绘挱 '" + announcer.getName()+"'");
							continue;
						}
					}
					//episode_id
					if(episode_id < 0 ){
						Episode episode = episodeService.getEpisodeByTitle("%"+keywords[i]+"%");
						if(episode != null){
							episode_id = episode.getId();
							UtilFunctions.serverLog("keywords '" + keywords[i] + "' 鎴愬姛鍖归厤闆嗛敠 '" + episode.getTitle()+"'");
							continue;
						}
					}
					//video_types
					if(video_types == null){
						VideoType  videoType = videoTypeService.getVideoTypeByName(keywords[i]);
						if(videoType != null){
							video_types = "%,"+videoType.getId()+",%";
							UtilFunctions.serverLog("keywords '" + keywords[i] + "' 鎴愬姛鍖归厤瑙嗛绫诲埆: '" + videoType.getName()+"'");
							continue;
						}
					}
				}
				//StringBuilder strB = new StringBuilder();
				
				String cacheIndex1=REQUEST_TYPE +"," + SEARCH_VIDEO_BY_KEYWORDS+ "judge cache => " + "heros:" +heros + ",announcers:" + ((announcers==null)?"":announcers) + ",episode:" + ((episode_id > 0)?"":episode_id)+ ",video_types:" + ((video_types==null)?"":video_types)+ ",start:" + start + ",limit:" + limit;
				
				UtilFunctions.serverLog("cacheIndex:"+cacheIndex1);
				Element ele = null;
				if((ele= cache.get(cacheIndex1))!= null){
					responseData = (String) ele.getValue();
					UtilFunctions.serverLog(REQUEST_TYPE+CACHE_DATA+cacheIndex1);
				}else{//缂撳瓨涓病鏈夋暟鎹�					
					JSONObject data = new JSONObject();
					VideoService videoService = (VideoService) context.getBean("videoService");
					int count = videoService.getVideoCount(null, heros, announcers, -1, video_types, episode_id);
					List<Video> videos = videoService.getVideos(null, heros, announcers, -1, video_types, episode_id, start, limit);
					data.put("count", count);
					JSONArray jRecords = new JSONArray();
					Iterator<Video> iter = videos.iterator();
					while(iter.hasNext()){
						jRecords.put(iter.next().getJSONObject());
					}
					data.put("videos", jRecords);
					responseData = data.toString();
					cache.put(new Element(cacheIndex1,responseData));
					UtilFunctions.serverLog(REQUEST_TYPE + NOT_CACHE_DATA+ cacheIndex1);
				}				
				cache.put(new Element(cacheIndex,responseData));
				//System.out.println(REQUEST_TYPE+NOT_CACHE_DATA);	
				UtilFunctions.serverLog(REQUEST_TYPE+NOT_CACHE_DATA + "keywords:" + strBuilder);
			}
			PrintWriter pw  = response.getWriter();
			pw.write(responseData);
			pw.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
	}
	
	private void logUserKeywords(String keyword){
		UserKeyword userKeyword = new UserKeyword();
		userKeyword.setKeyword(keyword);
		userKeyword.setCreated_at(new Date());
		userKeyword.setUpdated_at(new Date());
		UserKeywordService userKeywordService = (UserKeywordService) context.getBean("userKeywordService");
		userKeywordService.insert(userKeyword);
	}
	
}
