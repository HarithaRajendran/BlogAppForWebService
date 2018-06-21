package com.asahi.restapi.blogs.services;

import java.util.List;

import com.asahi.restapi.blogs.Exception.IdNotFoundException;
import com.asahi.restapi.blogs.Exception.DatasNotFoundException;
import com.asahi.restapi.blogs.dao.CommentDao;
import com.asahi.restapi.blogs.dao.CommentDaoImpl;
import com.asahi.restapi.blogs.entity.Comments;

public class CommentServiceImpl implements CommentService {

	CommentDao commentDao = new CommentDaoImpl();

	@Override
	public List<Comments> getAllComments(int blogId) {
		List<Comments> allCommentsBasedOnId = commentDao.getAllComments(blogId);
		if(allCommentsBasedOnId.size() == 0) {
			throw new DatasNotFoundException("No Content Found");
		}
		
		return allCommentsBasedOnId;
	}

	@Override
	public Comments getSelectedComment(int commentId) {
		Comments comments = commentDao.getSelectedComment(commentId);
		if(comments == null) {
			throw new IdNotFoundException(commentId+" is not found..");
		}
		
		return comments;
	}

	@Override
	public Comments addComment(Comments comments, int blogId) {
		return commentDao.addComment(comments, blogId);
	}

	@Override
	public String deleteComment(int commentId) {
		return commentDao.deleteComment(commentId);
	}

}
