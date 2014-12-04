package com.edu.thss.smartdental.model;

public class BBSElement {
	public String title;
	public String preview;
	public String time;
	public String tab;
	public String author;
	public boolean isCollected;
	public boolean isDeletable;
	
	public BBSElement(String title,String preview,String time, String author, boolean isCollected, boolean isDeletable){
		this.title = title;
		this.preview = preview;
		this.time =time;
		this.tab = "";
		this.author = author;
		this.isCollected = isCollected;
		this.isDeletable = isDeletable;
	}
}
