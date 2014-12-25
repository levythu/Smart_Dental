package com.edu.thss.smartdental.model;

import java.util.ArrayList;
import java.util.Iterator;

public class NewsElement {

	private String postID;
	private String postTitle;
	private String type;
	private ArrayList<String> userNameList;
	private ArrayList<String> newsIDList;
	private String time;
	
	public NewsElement(String postID, String postTitle, String type, ArrayList<String> userNameList, ArrayList<String> newsIDList, String time) {
		this.postID = postID;
		this.postTitle = postTitle;
		this.type = type;
		this.userNameList = userNameList;
		this.newsIDList = newsIDList;
		this.time = time;
	}
	
	public NewsElement() {
		this.userNameList = new ArrayList<String>();
	}
	
	public String getPostID() {
		return this.postID;
	}
	
	public String getPostTitle() {
		return this.postTitle;
	}
	
	public String getType() {
		return this.type;
	}
	
	public ArrayList<String> getUserNameList() {
		return this.userNameList;
	}
	
	public String getTime() {
		return this.time;
	}
	
	public void addUserToList(String userName) {
		Iterator<String> iterator = this.userNameList.iterator();
		
		while (iterator.hasNext()) {
			if (iterator.next().compareTo(userName) == 0) {
				return;
			}
		}
		this.userNameList.add(userName);
	}
	
	public int getUserListSize() {
		return this.userNameList.size();
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setPostID(String postID) {
		this.postID = postID;
	}
	
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	
	public ArrayList<String> getNewsIDList() {
		return this.newsIDList;
	}
	
	public void addNewsID(String newsID) {
		this.newsIDList.add(newsID);
	}
	
}
