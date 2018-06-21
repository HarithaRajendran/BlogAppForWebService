package com.asahi.restapi.blogs.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.asahi.restapi.blogs.Exception.IdNotFoundException;
import com.asahi.restapi.blogs.entity.Blogs;
import com.asahi.restapi.blogs.services.DBConnection;

public class BlogDaoImpl implements BlogDao,DBConnection {
	
	Connection con = null;

	public BlogDaoImpl() {
		con = connectionInstance();
	}
	@Override
	public List<Blogs> getAllBlogs() {

		List<Blogs> allBlogs = new ArrayList<>();

		try {
			Statement stmt = con.createStatement();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from blogs");

			while (rs.next()) {
				Blogs blogsInstance = new Blogs();

				blogsInstance.setId(rs.getInt(1));
				blogsInstance.setTitle(rs.getString(2));
				blogsInstance.setDescription(rs.getString(3));
				System.out.println(blogsInstance);

				allBlogs.add(blogsInstance);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("allBlogs " + allBlogs);
		return allBlogs;

	}

	@Override
	public Blogs getSingleBlog(int id) {
		List<Blogs> singleBlog = new ArrayList<>();
		Blogs specifiedBlog = null;
		try {
			Statement stmt = con.createStatement();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from blogs where id = "+id);

			while (rs.next()) {
				Blogs blogsInstance = new Blogs();
				
				blogsInstance.setId(rs.getInt(1));
				blogsInstance.setTitle(rs.getString(2));
				blogsInstance.setDescription(rs.getString(3));

				singleBlog.add(blogsInstance);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for(Blogs blog:singleBlog) {
			specifiedBlog = blog;
		}
		
		return specifiedBlog;
	}

	@Override
	public Blogs createBlog(Blogs blogs) {
	
		int newlyCreatedId = 0;
		
		String title = blogs.getTitle();
		String description = blogs.getDescription();
		Blogs blogsInstance = null;
		
		try {
			
			String insertionQuery = "INSERT INTO blogs (title,description) VALUES (?,?)" ;
			PreparedStatement pstmt = con.prepareStatement(insertionQuery,Statement.RETURN_GENERATED_KEYS);	
			
			pstmt.setString(1, title);
			pstmt.setString(2, description);
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			while(rs.next()) {
				newlyCreatedId = rs.getInt(1);
			}

			Statement stmt = con.createStatement();
			ResultSet rs1 = stmt.executeQuery("select * from blogs where id = "+ newlyCreatedId);
			
			while(rs1.next()) {
				blogsInstance = new Blogs();
				blogsInstance.setId(rs1.getInt(1));;
				blogsInstance.setTitle(rs1.getString(2));;
				blogsInstance.setDescription(rs1.getString(3));
			}
			
			
			System.out.println("Inserted Sucessfully...");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return blogsInstance;
	}
	
	@Override
	public String deleteBlog(int id) {
		
		String successMessage = null;
		
		try {
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("DELETE FROM comments WHERE blog_id = "+id);
			
			int nord = stmt.executeUpdate("DELETE FROM blogs WHERE id = "+id);
			
			if(nord>0) {
				successMessage = "ID with "+ id +" DELETE SUCCESSFULLY.......";
			}
			else {
				successMessage = "ID with "+ id +" Not Found.....";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return successMessage;
		
	}

	@Override
	public Blogs updateBlog(int id,Blogs updatedBlog) {
		
		
		String title = updatedBlog.getTitle();
		String description = updatedBlog.getDescription();
		Blogs blogsInstance = null;
		
		try {
			
			String updationQuery = " UPDATE blogs SET title = ? , description = ? WHERE id = ?";
			PreparedStatement pstmt = con.prepareStatement(updationQuery);	
			
			pstmt.setString(1, title);
			pstmt.setString(2, description);
			pstmt.setInt(3, id);
			pstmt.executeUpdate();
			
			Statement stmt = con.createStatement();
			ResultSet rs1 = stmt.executeQuery("select * from blogs where id = "+ id);
			
			while(rs1.next()) {
				blogsInstance = new Blogs();
				blogsInstance.setId(rs1.getInt(1));
				blogsInstance.setTitle(rs1.getString(2));
				blogsInstance.setDescription(rs1.getString(3));
			}
			
			
			System.out.println("Updated Sucessfully...");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return blogsInstance;
		
	}

	@Override
	public List<Blogs> SearchBlogByTitle(String title) {
		List<Blogs> SearchedBlogs = new ArrayList<>();
		try {
			Statement stmt = con.createStatement();
			stmt = con.createStatement();
			String query = "select * from blogs where title = '"+ title +"' ";
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				Blogs blogsInstance = new Blogs();
				
				blogsInstance.setId(rs.getInt(1));
				blogsInstance.setTitle(rs.getString(2));
				blogsInstance.setDescription(rs.getString(3));

				SearchedBlogs.add(blogsInstance);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return SearchedBlogs;
		
	}
	
	

}
