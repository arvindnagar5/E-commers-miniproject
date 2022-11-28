package com.java.miniproject.userDetails;

import java.sql.SQLException;
import java.util.Scanner;

import com.java.miniproject.SQLQuery.SqlQuery;
import com.java.miniproject.admin.Admin;

public class UserRegistration {
	boolean f = true;
	public int projectStart() throws SQLException {
		int uid=0;
		System.out.println("for login please enter \"1\" or for registration please enter \"2\" ");
		System.out.println("If you are admin enter - 3");
		SqlQuery sq = new SqlQuery();
		Scanner scan = new Scanner(System.in);
		int type = scan.nextInt();
		UserRegistration ur = new UserRegistration();
		
			if(type == 1) {
				uid = ur.userLogin();
			}else if(type == 2) {
				ur.userRegistration();
				System.out.println("Please login");
				ur.userLogin();
			}else if (type == 3) {
				
					Admin ad = new Admin();
					ad.adminLogin();
				while(true) {	
					System.out.println("All registered users list - 1 ");
					System.out.println("Available product - 2");
					int num = scan.nextInt();
					if(num==1) {
						sq.getUserDetails();
						System.out.println("for purches history enter user ID :");
						int id = scan.nextInt();
						sq.getPurchesHistory(id);
						System.out.println("For logout enter - 1 | or go to Home enter - 2");
						int num3 = scan.nextInt();
						if(num3==1) {
							System.out.println("successfully logout.....Bye..Bye!!");
							break;
						}else if (num==2) {
							f = true;
						}
					
					}else if (num==2) {
						ad.showAllProduct();
						break;
					}
				}				
			}else {
				System.out.println("please input valid number....");
				UserRegistration ur2 = new UserRegistration();
				ur2.projectStart();
			}
			
		return uid;
	}
	
	
	public int userLogin() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter username :");
		String email = scan.next();
		System.out.println("Enter Password");
		String Password = scan.next();
		SqlQuery sq = new SqlQuery();
		int uid = sq.loginCheck(email, Password);
		return uid;
		
	}
	
	
	
	public void userRegistration() {	
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your first name :");
		String firstName = scanner.next();
		System.out.println("Enter your last name :");
		String lastName = scanner.next();
		System.out.println("Enter your email id :");
		String email = scanner.next();
//		String email = null;
		/*int idx = mail.indexOf('@');
		if(idx == -1) {
			System.out.println("Please enter valid id");
		}else {
			if(idx <4) {
				System.out.println("Please enter valid id");
				mail = scanner.next();
			}
			email = mail;
		}*/
		System.out.println("Create password :");
		String password = scanner.next();
		UserDetails ud = new UserDetails(firstName,lastName,email,password);
		// store in DB
		SqlQuery sq = new SqlQuery();
		sq.insertUserDetailsToDB(ud);
		System.out.println("Your registration is successfull");
	}
	
	
}
