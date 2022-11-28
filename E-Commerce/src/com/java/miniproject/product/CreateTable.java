package com.java.miniproject.product;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.java.miniproject.connection.ConnectioCall;

public class CreateTable {
	Connection connection = null;
	
	public static void main(String[] args) {
		ConnectioCall con = new ConnectioCall();
		Connection connection = con.getConnectionDetail();
		System.out.println("Connection successfull");
		try {
			Statement stm = connection.createStatement();
			String query = "Create Table Purches_History (userId int,"
					+ "Name varchar(255),"
					+ "Description varchar(255),"
					+ "Price int(255),"
					+ "Quantity int(255),"
					+ "FOREIGN KEY(userId)"
					+ "REFERENCES users(UserId))";
			stm.execute(query);
			System.out.println("Table created");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
