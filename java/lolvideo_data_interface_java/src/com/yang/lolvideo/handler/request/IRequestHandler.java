package com.yang.lolvideo.handler.request;


public interface IRequestHandler {
	//public String getCacheIndex();
	//public void setCacheIndex();
	public static final String CACHE_DATA = "缂撳瓨鏁版嵁";
	public static final String NOT_CACHE_DATA = "闈炵紦瀛樻暟鎹�";
	public void doResponse();
	public void logRequestType();
}