package com.asahi.restapi.blogs.services;

import java.util.List;

import com.asahi.restapi.blogs.entity.Blogs;

public interface BlogService{
	
	public List<Blogs> getAllBlogs();
	public Blogs getSingleBlog(int id);
	public Blogs createBlog(Blogs blogs);
	public String deleteBlog(int id);
	public Blogs updateBlog(int id,Blogs updatedBlog);
	public List<Blogs> SearchBlogByTitle(String title);
		
}
