package com.yang.lolvideo.util;

import java.util.Date;

public class UtilFunctions {
	public static void serverLog(String str){
		System.out.println(new Date().toLocaleString()+":"+str);
	}
}
