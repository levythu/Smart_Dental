package com.edu.thss.smartdental.model;

public class CommentElement {
	public String content;
	public String time;
	public String author;
	public int id;
	public boolean isDeletable;
	
	public CommentElement(String content, String time, String author, int id){
		this.content = content;
		this.time = time;
		this.author = author;
		this.isDeletable = false;
		this.id = id;
	}

}
