package com.edu.thss.smartdental.model;

public class NewsElement {

	private String ID;
	private String type;
	private String userName;
	private String content;
	private String time;
	
	public NewsElement(String ID, String type, String userName, String content, String time) {
		this.ID = ID;
		this.type = type;
		this.userName = userName;
		this.content = content;
		this.time = time;
	}
	
	public String getID() {
		return this.ID;
	}
	
	public String getType() {
		return this.type;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public String getTime() {
		return this.time;
	}
	
}
