package com.java.miniproject.SQLQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.java.miniproject.connection.ConnectioCall;
import com.java.miniproject.userDetails.UserDetails;
import com.java.miniproject.userDetails.UserRegistration;

public class SqlQuery {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	Statement stm;
	
	public void showProduct() {
		
		ConnectioCall cc = new ConnectioCall();
		con = cc.getConnectionDetail();
		try {
			stm = con.createStatement();
			String qry = "SELECT ProductId,Name,Description,Price FROM products order by price ASC";
			rs = stm.executeQuery(qry);
			System.out.println("product Id"+"-------"+"name"+"--------------"+"price"+"/-");
			System.out.println("_____________________________________________________");
			while(rs.next()) {
				String Pid = rs.getString("ProductId");
				String name = rs.getString("Name");
				String Description =  rs.getString("Description");
				int price = rs.getInt(4);
				System.out.println(Pid+"------------"+name+"------------"+price+"/-");
				System.out.println("_____________________________________________________");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				con.close();
				stm.close();
				rs.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}
	
	public int loginCheck(String email,String password) {
		int uid = 0;
		ConnectioCall cc = new ConnectioCall();
		con = cc.getConnectionDetail();
		String select = "Select * from `e-commerce`.users  where email = '"+email+"' AND password = '"+password+"'";
		try {
			
			Statement stm = con.createStatement();
			rs = stm.executeQuery(select);
			if(rs.next()) {
				uid = rs.getInt(1);
				System.out.println("Login successfull.....");
				System.out.println("Welcome to E-Commerce....");
				
			}else {
				System.out.println("Your username or password incorect, Please try again.....");
				UserRegistration ur = new UserRegistration();
				ur.userLogin();
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
		
			try {
				con.close();
				rs.close();
			
			} catch (SQLException e) {
				
				
			}
			
		}
		return uid;
		
	}
	public void insertUserDetailsToDB (UserDetails ud) {
		//Create Connection
		try {
			ConnectioCall cc = new ConnectioCall();
			con = cc.getConnectionDetail();
			String insert = "insert into users(firstname,lastname,email,password)"
					+ "values(?,?,?,?)";
			//Prepared statement 
		
			pstmt = con.prepareStatement(insert);
			pstmt.setString(1, ud.getFirstName());//(1,arvind)
			pstmt.setString(2, ud.getLastName());
			pstmt.setString(3, ud.getEmail());
			pstmt.setString(4, ud.getPassword());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
		
			try {
				con.close();
				pstmt.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
	}
	
		
			
			public ResultSet selectProduct(int productId)  {
				ResultSet rs1 = null;
				ConnectioCall cc = new ConnectioCall();
				con = cc.getConnectionDetail();
				String qry = "SELECT ProductId,name,description,price from `e-commerce`.products where ProductId = '"+productId+"'";
//				try {
					try {
						stm = con.createStatement();
						rs = stm.executeQuery(qry);
						rs1 = rs;
				
						}
					 catch (SQLException e) {
						
						e.printStackTrace();
					}
					
					
					return rs;

				
			}
			public void addCart(int uid,int id,String name,String Description,int price,int quantity) {
				ConnectioCall cc = new ConnectioCall();
				con = cc.getConnectionDetail();
				String select = "INSERT INTO Cart (Userid,ProductId,name,Description,price,Quantity) value('"+uid+"','"+id+"','"+name+"','"+Description+"',"
						+ "'"+price+"','"+quantity+"')";
				try {
					stm = con.createStatement();
					stm.execute(select);
				} catch (SQLException e) {
					
					e.printStackTrace();
				}finally {
					
					try {
						con.close();
//						pstmt.close();
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
					
				}
			}
			public void goToCart() {
				ConnectioCall cc = new ConnectioCall();
				con = cc.getConnectionDetail();
				String sql = "SELECT * FROM `e-commerce`.cart order by price ASC ";
				try {
					stm = con.createStatement();
					ResultSet rs = stm.executeQuery(sql);
					while (rs.next()) {
						
						String pid = rs.getString(2);
						String Name = rs.getString(3);
						String Description = rs.getString(4);
						int price = rs.getInt(5);
						int qty = rs.getInt(6);
						System.out.println("Product ID - "+pid);
						System.out.println("product name - "+Name);
						System.out.println("product Description - "+Description);
						System.out.println("Price - "+price);
//						System.out.println(pid+"------------"+Name+"------------"+Description+"------------"+price+"/-");
					}
				} catch (SQLException e) {
					
					e.printStackTrace();
				}finally {
					try {
						con.close();
						stm.close();
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
					
				}
			}
			public void totalCartValue() throws SQLException {
				ConnectioCall cc = new ConnectioCall();
				con = cc.getConnectionDetail();
				String str = "SELECT sum(price) FROM `e-commerce`.cart ";
				stm = con.createStatement();
				rs = stm.executeQuery(str);
				while(rs.next()) {
					int prc = rs.getInt(1);
//					int qty = rs.getInt(2);
					System.out.print("Total Price - "+prc);
				}
				String str2 = "SELECT sum(Quantity) FROM `e-commerce`.cart ";
				stm = con.createStatement();
				rs = stm.executeQuery(str2);
				while(rs.next()) {
//					int prc = rs.getInt(1);
					int qty = rs.getInt(1);
					System.out.println("    Total Quantity - "+qty);
				}
				
				con.close();
				stm.close();
				rs.close();
			}
			public void emptyCart( )throws SQLException {
				ConnectioCall cc = new ConnectioCall();
				con = cc.getConnectionDetail();
				String trunk = "TRUNCATE `e-commerce`.cart";
				stm = con.createStatement();
				stm.execute(trunk);
				con.close();
				stm.close();
			}
			public void orderPlace()throws SQLException {
				ConnectioCall cc = new ConnectioCall();
				con = cc.getConnectionDetail();
				String copy = "INSERT into `e-commerce`.purches_history SELECT * from `e-commerce`.cart";
				stm = con.createStatement();
				stm.execute(copy);
			}
			public void moreProduct() throws SQLException {
				int uid=0;
				
				SqlQuery sq = new SqlQuery();
				sq.showProduct();
				System.out.println("Select product id as product :");
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
				//User details
				public void getUserDetails() throws SQLException {
					ConnectioCall cc = new ConnectioCall();
					con = cc.getConnectionDetail();
					String qry = "SELECT userid,firstname,lastname,email From `e-commerce`.users";
					stm = con.createStatement();
					ResultSet rs = stm.executeQuery(qry);
					while(rs.next()) {
						String uid = rs.getString(1);
						String fname = rs.getString(2);
						String lname = rs.getString(3);
						String email = rs.getString(4);
						System.out.println("User Id - "+uid);
						System.out.println("Name - "+(fname+" "+lname));
						System.out.println("Email - "+email);
						System.out.println("___________________________________");
					}
					con.close();
					stm.close();
				}
				//purches history
				public void getPurchesHistory(int option) throws SQLException {
					ConnectioCall cc = new ConnectioCall();
					con = cc.getConnectionDetail();
					String str = "SELECT ProductId,Name,Description,price,Quantity FROM `e-commerce`.purches_history where UserId = '"+option+"'";
					stm = con.createStatement();
					rs = stm.executeQuery(str);
					while(rs.next()) {
						String pid = rs.getString(1);
						String Name =  rs.getString(2);
						String des = rs.getString(3);
						String qty = rs.getString(4);
						System.out.println("Product Id - "+pid);
						System.out.println("Name - "+Name);
						System.out.println("Description - "+des);
						System.out.println("Quantity - "+qty);
						System.out.println("___________________________________");
					}
					con.close();
					stm.close();
				}
				
				public void deleteQty() {
					ConnectioCall cc = new ConnectioCall();
					con = cc.getConnectionDetail();
					String str = "UPDATE  `e-commerce`.products set quantity = '5' where productid = 1";
				}
				
				
			
			
	
}
