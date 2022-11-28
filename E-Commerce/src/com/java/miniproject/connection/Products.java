package com.java.miniproject.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Products {
	PreparedStatement ps = null;
	public static void main(String[] args) throws SQLException {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter products Qty :");
		int num = scan.nextInt();
		for(int i = 1; i<=num; i++) {
			System.out.println("Enter product name :");
			String name = scan.next();
			System.out.println("Enter product Description :");
			String Description = scan.next();
			System.out.println("Enter product price :");
			int price = scan.nextInt();
			System.out.println("Enter product quantity :");
			int quantity = scan.nextInt();
			Products products = new Products();
			products.insertProducts(name, Description, price, quantity);
			
		}
		System.out.println(num +" Product added...");
		scan.close();
		
		
		
		
	}
	
	public void insertProducts(String name, String Description,int price,int Quantity) throws SQLException {
		ConnectioCall con = new ConnectioCall();
		Connection connection = con.getConnectionDetail();
		try {
			 ps = connection.prepareStatement("insert into products (name,Description,price,quantity) "
					+ "values(?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, Description);
			ps.setInt(3, price);
			ps.setInt(4, Quantity);
			int n = ps.executeUpdate();
			System.out.println(n+" Row added");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			connection.close();
			ps.close();
		}
		
	}
}
