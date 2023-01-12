package com.abc.jeevika.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CheckCredentials {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//JDBCAPI required parameters
				Connection connection=null;
				Statement statement=null;
				ResultSet resultSet=null;
				
				//To connect with DB Engine using url,userName,password
				String url="jdbc:mysql:///Students";
				String userName="root";
				String password="root@123";
				
				//collect the query from user and process for execution
				BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
				//String query=br.readLine();
				
				try {
					//Establishing the connection with DB
					connection=DriverManager.getConnection(url,userName,password);
					if(connection!=null){
						//Create a statement object to transfer with DB
						statement=connection.createStatement();
						if(statement!=null) {
							//Processing given query and get updated result
							System.out.print("Enter your username:: ");
							String username=br.readLine();
							
							System.out.print("Enter your password:: ");
							String pwd=br.readLine();
							
							String sqlSelectQuery="select count(*) from UserInfo where username='"+username+"' and password='"+pwd+"'";
							System.out.println(sqlSelectQuery);
							resultSet=statement.executeQuery(sqlSelectQuery);
							if(resultSet!=null) {
								//step=>process the resultSet
								if(resultSet.next()) {
									int rowAffected=resultSet.getInt(1);
									
									if(rowAffected==0) {
										System.out.println("Invalid Credentials....");
									}else {
										System.out.println("Valid Credentials...");
									}
								}
							}
						}
						
					}
				}catch(SQLException ex) {
					ex.printStackTrace();
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					//Closing the resourses
					if(br!=null) {
						try {
							br.close();
						}catch(Exception ex) {
							ex.printStackTrace();
						}
					}
					if(statement!=null) {
						try {
							statement.close();
						}catch(SQLException ex) {
							ex.printStackTrace();
						}
					}
					if(resultSet!=null) {
						try {
							resultSet.close();
						}catch(SQLException ex) {
							ex.printStackTrace();
						}
					}
					if(connection!=null) {
						try {
							connection.close();
						}catch(SQLException ex) {
							ex.printStackTrace();
						}
					}
				}
	}

}
