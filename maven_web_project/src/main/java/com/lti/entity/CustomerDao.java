package com.lti.entity;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class CustomerDao {
	public void addCustomer(Customer customer) {
	Connection conn = null;
	PreparedStatement stmt = null;
	try {
		Properties dbProps=new Properties();
		dbProps.load(this.getClass().getClassLoader().getResourceAsStream("prod-db.properties"));
		Class.forName(dbProps.getProperty("driver"));
		conn = DriverManager.getConnection(dbProps.getProperty("url"),dbProps.getProperty("user"),dbProps.getProperty("pass"));
		String sql = "insert into customerb values(?,?,?)";
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, customer.getId());
		stmt.setString(2, customer.getName());
		stmt.setString(3, customer.getEmail());
		stmt.executeUpdate();
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}catch(IOException e) {
		e.printStackTrace();
	}
	finally {
		try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	}
}

	