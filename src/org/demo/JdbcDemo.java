package org.demo;

import org.demo.impl.JdbcDaoImpl;
import org.demo.model.Employee;

public class JdbcDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Employee circle = new JdbcDaoImpl().getEmployee(7369);
		System.out.println(circle.getName());
	}

}
