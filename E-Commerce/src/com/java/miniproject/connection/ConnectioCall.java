package com.java.miniproject.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectioCall {
Connection connection = null;
	
	public Connection getConnectionDetail() {
	
		try {
			//Load Driver
//			Class.forName("com.mysql.jdbc.Driver");
//			System.out.println("Driver load Successefull...");
			// Create Connection
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/e-commerce", "root", "root");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
}
