package com.asahi.restapi.blogs.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Comments {
	
	private int id;
	private String comment;
	Date currentTime = new Date();
	private List<Links> links = new ArrayList<>();
	
	java.text.SimpleDateFormat sdf = 
		     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private	String createdAt = sdf.format(currentTime);

	public Comments() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	public List<Links> getLinks() {
		return links;
	}

	public void setLinks(List<Links> links) {
		this.links = links;
	}
	
	public void addLink(String url,String rel) {
		Links link = new Links();
		
		link.setUrl(url);
		link.setRel(rel);
		
		links.add(link);	
	}

	@Override
	public String toString() {
		return "Comments [id=" + id + ", comment=" + comment + ", createdAt=" + createdAt + "]";
	}

	
	
	
}
