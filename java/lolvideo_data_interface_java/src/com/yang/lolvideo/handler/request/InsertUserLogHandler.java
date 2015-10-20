package com.yang.lolvideo.handler.request;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;

import com.yang.lolvideo.model.UserLog;
import com.yang.lolvideo.service.UserLogService;

public class InsertUserLogHandler {
	private HttpServletRequest request;
	private ApplicationContext context;
	public InsertUserLogHandler(HttpServletRequest request, ApplicationContext context){
		this.request = request;
		this.context = context;
	}
	public void execute(){
		UserLog userLog = new UserLog();
		userLog.setIp(request.getRemoteAddr());
		userLog.setUrl(request.getRequestURI());
		userLog.setQuery(request.getQueryString());
		userLog.setCreated_at(new Date());
		userLog.setUpdate_at(new Date());
		String version = request.getParameter("v");
		userLog.setVersion(version==null?"<1.0.0":version);
		String platform = request.getParameter("p");
		userLog.setPlatform(platform==null?"":platform);
		String mac = request.getParameter("mac");
		userLog.setMac(mac==null?"":mac);
		UserLogService userLogService = (UserLogService) context.getBean("userLogService");
		userLogService.insert(userLog);
	}
}
