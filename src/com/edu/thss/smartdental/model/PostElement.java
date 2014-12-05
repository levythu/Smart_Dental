package com.edu.thss.smartdental.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PostElement {
	private String title;
	private String content;
	private String author;
	private Date date;
	private Boolean onlyToDoctor;
	
	public PostElement() {
	}
	
	public PostElement (String title, String content, String author, Date date, Boolean onlyToDoctor) {
		this.title = title;
		this.content = content;
		this.author = author;
		this.date = date;
		this.onlyToDoctor = onlyToDoctor;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public void setAuthor(String author){
		this.author = author;
	}
	
	public String getAuthor() {
		return this.author;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public void setOnlyToDoctor(Boolean onlyToDoctor) { 
		this.onlyToDoctor = onlyToDoctor;
	}
	
	public Boolean getOnlyToDoctor() { 
		return this.onlyToDoctor;
	}
	
	public BBSElement display() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return new BBSElement(this.title, this.content.substring(0, 20), simpleDateFormat.format(this.date), author, !this.onlyToDoctor, true);
	}
}
