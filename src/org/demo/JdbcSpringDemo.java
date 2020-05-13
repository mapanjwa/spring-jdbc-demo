package org.demo;

import org.demo.impl.JdbcSpringDaoImpl;
import org.demo.model.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JdbcSpringDemo {
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		JdbcSpringDaoImpl dao = context.getBean("jdbcSpringDaoImpl",JdbcSpringDaoImpl.class);
		Employee circle = dao.getEmployee(7369);
		System.out.println(circle.getName());
	}
}
