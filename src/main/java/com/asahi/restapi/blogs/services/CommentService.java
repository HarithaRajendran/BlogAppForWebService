package com.asahi.restapi.blogs.services;

import java.util.List;

import com.asahi.restapi.blogs.entity.Comments;

public interface CommentService {
	
	public List<Comments> getAllComments(int blogId);
	public Comments getSelectedComment(int commentId);
	public Comments addComment(Comments comments,int blogId);
	public String deleteComment(int commentId);
}
