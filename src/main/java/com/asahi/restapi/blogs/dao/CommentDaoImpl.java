package com.asahi.restapi.blogs.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.asahi.restapi.blogs.entity.Blogs;
import com.asahi.restapi.blogs.entity.Comments;
import com.asahi.restapi.blogs.services.DBConnection;

public class CommentDaoImpl implements CommentDao,DBConnection {
	
	Connection con = null;

	public CommentDaoImpl() {
		con = connectionInstance();
	}

	@Override
	public List<Comments> getAllComments(int blogId) {

		List<Comments> allCommentsBasedOnBlogId = new ArrayList<>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from comments where blog_id = " + blogId);

			while (rs.next()) {
				Comments commentsInstance = new Comments();

				commentsInstance.setId(rs.getInt(1));
				commentsInstance.setComment(rs.getString(2));
				commentsInstance.setCreatedAt(rs.getString(3));

				allCommentsBasedOnBlogId.add(commentsInstance);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allCommentsBasedOnBlogId;
	}
	
	
	@Override
	public Comments getSelectedComment(int commentId) {
		
			List<Comments> selectedComment = new ArrayList<>();
			Comments SelectedComent = null;
			try {
				Statement stmt = con.createStatement();
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select * from comments where id = "+commentId);

				while (rs.next()) {
					Comments commentsInstance = new Comments();
					
					commentsInstance.setId(rs.getInt(1));
					commentsInstance.setComment(rs.getString(2));
					commentsInstance.setCreatedAt(rs.getString(3));

					selectedComment.add(commentsInstance);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			for(Comments comment:selectedComment) {
				SelectedComent = comment;
			}
			
			return SelectedComent;
		}


	@Override
	public Comments addComment(Comments comments, int blogId) {
		int newlyCreatedId = 0;

		String comment = comments.getComment();
		Comments commentInstance = null;

		try {

			String insertionQuery = "INSERT INTO comments (comment,blog_id) VALUES (?,?)";
			PreparedStatement pstmt = con.prepareStatement(insertionQuery, Statement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, comment);
			pstmt.setInt(2, blogId);
			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();
			while (rs.next()) {
				newlyCreatedId = rs.getInt(1);
			}

			Statement stmt = con.createStatement();
			ResultSet rs1 = stmt.executeQuery("select * from comments where id = " + newlyCreatedId);

			while (rs1.next()) {
				commentInstance = new Comments();
				commentInstance.setId(rs1.getInt(1));
				commentInstance.setComment(rs1.getString(2));
				commentInstance.setCreatedAt(rs1.getString(3));
			}

			System.out.println("Inserted Sucessfully...");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return commentInstance;
	}

	@Override
	public String deleteComment(int commentId) {

		String successMessage = null;

		try {

			Statement stmt = con.createStatement();
			int nord = stmt.executeUpdate("DELETE FROM comments WHERE id = " + commentId);

			if (nord > 0) {
				successMessage = "ID with " + commentId + " DELETE SUCCESSFULLY.......";
			} else {
				successMessage = "ID : " + commentId + " NOT FOUND";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return successMessage;
	}

	
}
