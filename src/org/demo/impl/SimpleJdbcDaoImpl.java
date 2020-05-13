package org.demo.impl;

import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

// demo using DAO support using SimpleJdbcDaoSupport
//we can extend JDBCTemplate or NamedparameterJDBCTemplate as well
public class SimpleJdbcDaoImpl extends SimpleJdbcDaoSupport{

	public int getEmployeeCountUsingSimpleJDBC() {
		String sql = "SELECT count(*) from employees";
		//Assigning the Data Source to JDBC Template : not required as 
		//Datasource is initialized in spring.xml of Spring.
		//jdbcTemplate.setDataSource(getDataSource());
		return this.getJdbcTemplate().queryForInt(sql);
	}
	
}
