package com.java.miniproject.start;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.java.miniproject.SQLQuery.SqlQuery;
import com.java.miniproject.connection.ConnectioCall;
import com.java.miniproject.userDetails.UserRegistration;

public class ProjectStart {
	static Connection con;
	
	public static void main(String[] args) throws SQLException {
		int uid=0;
		boolean f = true;
		int num = 0;
		UserRegistration ur = new UserRegistration();
		uid = ur.projectStart();
		SqlQuery sq = new SqlQuery();
		
		//input for product selection from user 
		Scanner scan = new Scanner(System.in);
	while(f) {	
		sq.showProduct();
		System.out.println("Enter product Id for product details :");
		int productId = scan.nextInt();
		ResultSet rs = sq.selectProduct(productId);
		while(rs.next()) {
			int ProductId = rs.getInt(1);
			System.out.println("Product Id - "+ProductId);
			String name = rs.getString("name");
			System.out.println("Product name - "+name);
			String description = rs.getNString("description");
			System.out.println("Product Description - "+description);
			int price = rs.getInt(4);
			System.out.println("Price - "+price+"/-");
			System.out.println("__________________________________________________________________________________");
		System.out.println("Add to cart enter - 1 | Buy now enter - 2");
		int next = scan.nextInt();
		int qty=0;
		if (next == 1) {
			System.out.println("Enter quantity :");
				qty = scan.nextInt();
				sq.addCart(uid,ProductId, name, description, price, qty);
				System.out.println("Product is added to cart");
			}else if (next == 2) {
				sq.orderPlace();
				sq.emptyCart();
				System.out.println("Order successfully placed.....");
				System.out.println("Thank you! ");
				break;
			}else {
				System.out.println("invalid input....");
				System.out.println("Try again...");				
				}
		System.out.println("Go to cart enter - 1 | for more product enter - 2 ");
		num = scan.nextInt();
		if(num==1) {
			f = false;
		}else if(num == 2){
			f=true;
		}
		
	}	
		if(num==1) {
			sq.goToCart();
			sq.totalCartValue();
			System.out.println("Buy now enter - 1 | Logout enter - 2");
			int inter = scan.nextInt();
			if(inter == 1) {
				sq.orderPlace();
				sq.emptyCart();
				System.out.println("Order successfully placed.....");
				System.out.println("Thank you! ");
				break;
			}else if (inter == 2) {
				System.out.println("Thank you!!!");
				sq.emptyCart();
				break;
			}
		}else if (num == 2) {
			
		}else {
			System.out.println("Invalid input...");
			System.out.println("Choose again...");
		}
		System.out.println("");
			
		}
	}
}
