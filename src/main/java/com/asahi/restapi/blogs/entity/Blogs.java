package com.asahi.restapi.blogs.entity;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Blogs {

	private int id;
	private String title;
	private String description;
	private List<Links> links = new ArrayList<>();

	public Blogs() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		return "Blogs [id=" + id + ", title=" + title + ", description=" + description + ", links=" + links + "]";
	}

}
