package com.java.miniproject.start;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.java.miniproject.SQLQuery.SqlQuery;
import com.java.miniproject.userDetails.UserRegistration;

public class label {
		public void moreProduct() throws SQLException {
			int uid=0;
			UserRegistration ur = new UserRegistration();
			uid = ur.projectStart();
			SqlQuery sq = new SqlQuery();
			sq.showProduct();
			//input for product selection from user 
			Scanner scan = new Scanner(System.in);
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
//					ConnectioCall cc = new ConnectioCall();
//					con = cc.getConnectionDetail();
//					Statement stm = con.createStatement();
//					String query = "SELECT * FROM `e-commerce`.cart where ProductId = '"+ProductId+"'";
//					ResultSet rs2 = stm.executeQuery(query);
//					if (rs2.next()) {
//						String update = "UPDATE `e-commerce`.cart set Quantity = 1 where id = '"+ProductId+"'";
//					}
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
			}
		}
}
