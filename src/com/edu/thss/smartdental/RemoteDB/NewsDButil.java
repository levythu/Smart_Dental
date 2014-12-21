package com.edu.thss.smartdental.RemoteDB;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NewsDButil {
	private ArrayList<String> parametername = new ArrayList<String>();
	private ArrayList<String> parametervalue = new ArrayList<String>();
	private ArrayList<String> resultinfo = new ArrayList<String>();
	private HttpConnSoap Soap = new HttpConnSoap();
	/**
	 * 获得用户全部新消息
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> selectAllUnreadNewsByUsername(String username) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		parametername.clear();
		parametervalue.clear();
		resultinfo.clear();
		parametername.add("username");
		parametervalue.add(username);
		try{
			
			resultinfo = Soap.GetWebService("selectAllUnreadNewsByUsername", parametername, parametervalue);
		}
		catch(Exception e) {
		}

		HashMap<String, String> tempHash = new HashMap<String, String>();
		tempHash.put("newsId", "newsId");
		tempHash.put("newstype", "newstype");
		tempHash.put("username", "username");
		tempHash.put("content", "content");
		tempHash.put("time", "time");
		list.add(tempHash);
		
		for (int j = 0; j < resultinfo.size(); j += 5) {
			HashMap<String, String> hashMap = new HashMap<String, String>();
			hashMap.put("newsId", resultinfo.get(j));
			hashMap.put("newstype", resultinfo.get(j + 1));
			hashMap.put("username", resultinfo.get(j + 2));
			hashMap.put("content", resultinfo.get(j + 3));
			hashMap.put("time", resultinfo.get(j + 4));
			list.add(hashMap);
		}

		return list;
	}
	/**
	 * 获取用户全部已读消息
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> selectAllReadNewsByUsername(String username) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		parametername.clear();
		parametervalue.clear();
		resultinfo.clear();
		parametername.add("username");
		parametervalue.add(username);
		try{
			
			resultinfo = Soap.GetWebService("selectAllReadNewsByUsername", parametername, parametervalue);
		}
		catch(Exception e) {
		}

		HashMap<String, String> tempHash = new HashMap<String, String>();
		tempHash.put("newsId", "newsId");
		tempHash.put("newstype", "newstype");
		tempHash.put("username", "username");
		tempHash.put("content", "content");
		tempHash.put("time", "time");
		list.add(tempHash);
		
		for (int j = 0; j < resultinfo.size(); j += 5) {
			HashMap<String, String> hashMap = new HashMap<String, String>();
			hashMap.put("newsId", resultinfo.get(j));
			hashMap.put("newstype", resultinfo.get(j + 1));
			hashMap.put("username", resultinfo.get(j + 2));
			hashMap.put("content", resultinfo.get(j + 3));
			hashMap.put("time", resultinfo.get(j + 4));
			list.add(hashMap);
		}

		return list;
	}
	
	/**
	 * 新建一条消息
	 * 
	 * @return
	 */
	public String insertNews(String newstype, String username, String replytouser, String haveread,String newscontent) {

		parametername.clear();
		parametervalue.clear();
		resultinfo.clear();
		
		parametername.add("newstype");
		parametername.add("username");
		parametername.add("replytouser");
		parametername.add("haveread");
		parametername.add("newscontent");
		parametervalue.add(newstype);
		parametervalue.add(username);
		parametervalue.add(replytouser);
		parametervalue.add(haveread);	
		parametervalue.add(newscontent);
		try{
			resultinfo = Soap.GetWebService("insertNews", parametername, parametervalue);
		}
		catch(Exception e) {
		}	
		if(resultinfo.size() == 0){
			return "fail to connect to Database";
		}
		return resultinfo.get(0);
	}
	
	/**
	 * 更新消息为已读
	 * 
	 * @return
	 */
	public String haveread(String newsid) {

		parametername.clear();
		parametervalue.clear();
		resultinfo.clear();
		
		parametername.add("newsid");
		parametervalue.add(newsid);
		try{
			resultinfo = Soap.GetWebService("haveread", parametername, parametervalue);
		}
		catch(Exception e) {
		}
		if(resultinfo.size() == 0){
			return "fail to connect to Database";
		}
		return resultinfo.get(0);
	}
}
