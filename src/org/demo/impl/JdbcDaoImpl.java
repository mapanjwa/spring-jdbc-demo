package org.demo.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.driver.OracleConnection;

import org.demo.model.Employee;

public class JdbcDaoImpl {

	public Employee getEmployee (int empId) {
		
		
		Connection conn =  null;
		
		String usr = "scott";
        String pass = "tiger";
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        
        try {
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
			System.out.print("Starting database connection: ");
			conn = (OracleConnection) DriverManager.getConnection(url, usr, pass);
			
			PreparedStatement ps = conn.prepareStatement("SELECT * from employees where eno = ?");
			ps.setInt(1, empId);
			Employee emp = null;
			ResultSet rs =  ps.executeQuery();
			
			if (rs.next()) {
			 emp = new Employee(empId, rs.getString("ENAME"));	
			}
			rs.close();
			ps.close();
			return emp;
        } catch (SQLException e) {
        	throw new RuntimeException();
        } finally {
        	try {
				conn.close();
			} catch (SQLException e) {}
        }
	}
}
