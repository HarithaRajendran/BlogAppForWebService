package com.asahi.restapi.blogs.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface DBConnection
{
	String mysqlConnectionUrl = "jdbc:mysql://localhost:3306/BlogApplicationRestApi";
	String user = "root";
	String password = "root";
	
	
	default Connection connectionInstance() {

		Connection con2 = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("connection");
			Connection con =  DriverManager.getConnection(mysqlConnectionUrl, user, password);
			con2 = con;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con2;
		
	}

}
