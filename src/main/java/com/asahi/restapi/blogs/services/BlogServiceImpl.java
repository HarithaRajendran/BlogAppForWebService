package com.asahi.restapi.blogs.services;

import java.util.List;

import com.asahi.restapi.blogs.Exception.IdNotFoundException;
import com.asahi.restapi.blogs.dao.BlogDao;
import com.asahi.restapi.blogs.dao.BlogDaoImpl;
import com.asahi.restapi.blogs.entity.Blogs;

public class BlogServiceImpl implements BlogService{
	
	BlogDao blogDao = new BlogDaoImpl();

	@Override
	public List<Blogs> getAllBlogs() {
		
		return blogDao.getAllBlogs();
	}

	@Override
	public Blogs getSingleBlog(int id) {
		Blogs blogs = blogDao.getSingleBlog(id);
		if(blogs == null) {
			throw new IdNotFoundException(id+" is not found..");
		}
		
		return blogs;
	}

	@Override
	public Blogs createBlog(Blogs blogs) {
		return blogDao.createBlog(blogs);
	}

	@Override
	public String deleteBlog(int id) {
		return blogDao.deleteBlog(id);
	}

	@Override
	public Blogs updateBlog(int id, Blogs updatedBlog) {
		return blogDao.updateBlog(id, updatedBlog);
	}

	@Override
	public List<Blogs> SearchBlogByTitle(String title) {
		return blogDao.SearchBlogByTitle(title);
	}
	
}
