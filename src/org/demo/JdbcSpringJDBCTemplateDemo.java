package org.demo;

import java.sql.Date;

import org.demo.impl.JdbcSpringDaoJDBCTemplateImpl;
import org.demo.impl.SimpleJdbcDaoImpl;
import org.demo.model.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JdbcSpringJDBCTemplateDemo {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		JdbcSpringDaoJDBCTemplateImpl dao = context.getBean("jdbcSpringDaoJDBCTemplateImpl",JdbcSpringDaoJDBCTemplateImpl.class);
		System.out.println("Employee Name:"+ dao.getEmployeeName(7369));	
		System.out.println("Total number of Employees:"+ dao.getEmployeeCount());
		System.out.println("Employee ID:" + dao.getEmployee(7369).getId() +
				"  Employee Name:" + dao.getEmployee(7369).getName());
		
		System.out.println("Total Employees:" + dao.getAllEmployees().size());
		
		
		//dao.insertEmployee(new Employee(7935, "Mayur", "Manager", 7934, new Date(1989, 10, 13), 50000.0, 5.0, 10));
		System.out.println("Total Employees after Insert:" + dao.getAllEmployees().size());
		
		//below will create a Triangle table
		//dao.createTriangleTable();
		
		//dao.insertEmployeeNamedParam(new Employee(7936, "Koushik", "Manager", 7934, new Date(1982, 10, 13), 70000.0, 5.0, 10));
		System.out.println("Total Employees after Insert:" + dao.getAllEmployees().size());
		
		//DAO Support
		SimpleJdbcDaoImpl dao1 = context.getBean("simpleJdbcDaoImpl",SimpleJdbcDaoImpl.class);
		System.out.println("Employee count using Simple JDBC:"+ dao1.getEmployeeCountUsingSimpleJDBC());	
		
		
		
	}

}
