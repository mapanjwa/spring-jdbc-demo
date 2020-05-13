package org.demo.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import oracle.jdbc.driver.OracleConnection;

import org.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


// @Component will check : component scan tag is used in spring.xml
//this class will be registered as spring bean with name "jdbcSpringDaoImpl"
@Component
public class JdbcSpringDaoImpl {
	
	@Autowired
	private DataSource dataSource;

	
	public Employee getEmployee (int empId) {
		
		Connection conn =  null;
		
		//String usr = "scott";
        //String pass = "tiger";
        //String url = "jdbc:oracle:thin:@localhost:1521:XE";
        try {
			
        	System.out.print("Starting database connection: ");
        	//DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
			//conn = (OracleConnection) DriverManager.getConnection(url, usr, pass);
			
			//connection details are added in the spring.xml
			conn = dataSource.getConnection();
			
			
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

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public DataSource getDataSource() {
		return dataSource;
	}
}
