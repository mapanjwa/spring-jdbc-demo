package org.demo.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;


import org.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class JdbcSpringDaoJDBCTemplateImpl {
	
	//The annotation is used at the setter method to make sure JDBCTemplate is also initialized
	//@Autowired
	private DataSource dataSource;

	//JDBC Template is initialized by the Spring itself while initializing datasource
	//private JdbcTemplate jdbcTemplate = new JdbcTemplate();
	private JdbcTemplate jdbcTemplate;
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	@Autowired
	public void setDataSource(DataSource dataSource) {
		//this.dataSource = dataSource;
		//below line and Autowired annotation on the setter method
		//will make sure JDBC template is initialized when datasource is initialized by Spring
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		
		//Initialize NamedParameterJdbcTemplate
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public DataSource getDataSource() {
		return dataSource;
	}
	
	//using JDBC Template
	public int getEmployeeCount() {
		String sql = "SELECT count(*) from employees";
		//Assigning the Data Source to JDBC Template : not required as we are initializing in setDataSource() method
		//jdbcTemplate.setDataSource(getDataSource());
		return jdbcTemplate.queryForInt(sql);
	}

	//using JDBC Template
	public String getEmployeeName(int empID) {
		String sql = "SELECT ename from employees where eno = ?";
		return jdbcTemplate.queryForObject(sql,new Object[] {empID}, String.class);
	}
	
	//using JDBC Template
	public Employee getEmployee(int empID) {
		String sql = "SELECT * from employees where eno = ?";
		return jdbcTemplate.queryForObject(sql,new Object[] {empID}, new EmployeeMapper());
	}
	
	//rowmapper can be used for object as well as for the list of object
	private static final class EmployeeMapper implements RowMapper<Employee> {
		public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
			Employee emp = new Employee();
			emp.setId(rs.getInt("eno"));
			emp.setName(rs.getString("ename"));
			return emp;
		}
	}
	
	//usingJDBC Template
	public List<Employee> getAllEmployees(){
		String sql = "SELECT * from employees";
		return jdbcTemplate.query(sql, new EmployeeMapper());
		
	}
	
	//usingJDBC Template
	public void insertEmployee (Employee emp) {
		String sql = "insert into EMPLOYEES (eno,ename,job,mgr,doj,sal,comm,dno) VALUES (?,?,?,?,?,?,?,?)";
		/*Map<String,Object> params = new HashMap<String, Object>(); 
		params.put("id", emp.getId());
		params.put("name", emp.getName());
		params.put("job", emp.getJob());
		params.put("mgrId", emp.getMgrId());
		params.put("doj", emp.getDoj());
		params.put("sal", emp.getSal());
		params.put("comm", emp.getComm());
		params.put("dno", emp.getDno());
		SqlParameterSource sqlParams = new MapSqlParameterSource(params);*/
		
		jdbcTemplate.update(sql, new Object[] {emp.getId(),emp.getName(),emp.getJob(),emp.getMgrId()
											  ,emp.getDoj(),emp.getSal(),emp.getComm(),emp.getDno()});
	}
	
	
	//usingJDBC Template
	public void createTriangleTable () {
		String sql =  "CREATE TABLE TRIANGLE (ID INTEGER, NAME VARCHAR(50))";
		jdbcTemplate.execute(sql);
	}
	
	
	//using Named Parameter JDBC Template
	public void insertEmployeeNamedParam (Employee emp) {
		String sql = "insert into EMPLOYEES (eno,ename,job,mgr,doj,sal,comm,dno) VALUES (:id,:name,:job,:mgrId,:doj,:sal,:comm,:dno)";
		Map<String,Object> params = new HashMap<String, Object>(); 
		params.put("id", emp.getId());
		params.put("name", emp.getName());
		params.put("job", emp.getJob());
		params.put("mgrId", emp.getMgrId());
		params.put("doj", emp.getDoj());
		params.put("sal", emp.getSal());
		params.put("comm", emp.getComm());
		params.put("dno", emp.getDno());
		SqlParameterSource sqlParams = new MapSqlParameterSource(params);
		namedParameterJdbcTemplate.update(sql, sqlParams);
	}

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}
	
	//without JDBCTemplate
	/*public Employee getEmployee (int empId) {
	
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
*/
	
	
}
