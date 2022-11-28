package com.java.miniproject.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.java.miniproject.SQLQuery.SqlQuery;
import com.java.miniproject.connection.ConnectioCall;
import com.java.miniproject.userDetails.UserRegistration;

public class Admin {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	Statement stm;
public void showAllProduct() {
		
		ConnectioCall cc = new ConnectioCall();
		con = cc.getConnectionDetail();
		try {
			stm = con.createStatement();
			String qry = "SELECT ProductId,Name,Description,Price,quantity FROM products order by price ASC";
			rs = stm.executeQuery(qry);
			System.out.println("product Id"+"-------"+"name"+"--------------"+"price"+"/-");
			System.out.println("_____________________________________________________");
			while(rs.next()) {
				String Pid = rs.getString("ProductId");
				String name = rs.getString("Name");
				String Description =  rs.getString("Description");
				int price = rs.getInt(4);
				int qty = rs.getInt(5);
				System.out.println(Pid+"------------"+name+"------------"+price+"/-"+"----------"+qty);
				System.out.println("_____________________________________________________");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				con.close();
				stm.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void adminLogin() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter username :");
		String email = scan.next();
		System.out.println("Enter Password");
		String Password = scan.next();
		Admin ad = new Admin();
		ad.adminLoginCheck(email, Password); 
		
		
	}
	public void adminLoginCheck(String email,String password) {
		int uid = 0;
		ConnectioCall cc = new ConnectioCall();
		con = cc.getConnectionDetail();
		String select = "Select * from `e-commerce`.admindetail  where email = '"+email+"' AND password = '"+password+"'";
		try {
			
			Statement stm = con.createStatement();
			rs = stm.executeQuery(select);
			if(rs.next()) {
				uid = rs.getInt(1);
				System.out.println("Login successfull.....");
				System.out.println("Welcome to admin panel.");
				
			}else {
				System.out.println("Your username or password incorect, Please try again.....");
				Admin ad = new Admin();
				ad.adminLogin();
				
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
		
			try {
				con.close();
				rs.close();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				
			}
			
		}
		
		
	}

}
