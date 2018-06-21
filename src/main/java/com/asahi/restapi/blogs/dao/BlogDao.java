package com.asahi.restapi.blogs.dao;

import java.util.List;

import com.asahi.restapi.blogs.entity.Blogs;

public interface BlogDao {
	
	public List<Blogs> getAllBlogs();
	public Blogs getSingleBlog(int id);
	public Blogs createBlog(Blogs blogs);
	public String deleteBlog(int id);
	public Blogs updateBlog(int id,Blogs updatedBlog);
	public List<Blogs> SearchBlogByTitle(String title);

}
