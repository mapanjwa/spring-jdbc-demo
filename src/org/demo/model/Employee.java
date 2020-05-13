package org.demo.model;

import java.sql.Date;

public class Employee {

	private int id;
	private String name;
	
	private String job;
	private int mgrId;
	private Date doj;
	private double sal;
	private double comm;
	private int dno;
	
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public int getMgrId() {
		return mgrId;
	}
	public void setMgrId(int mgrId) {
		this.mgrId = mgrId;
	}
	public Date getDoj() {
		return doj;
	}
	public void setDoj(Date doj) {
		this.doj = doj;
	}
	public double getSal() {
		return sal;
	}
	public void setSal(double sal) {
		this.sal = sal;
	}
	public double getComm() {
		return comm;
	}
	public void setComm(double comm) {
		this.comm = comm;
	}
	public int getDno() {
		return dno;
	}
	public void setDno(int dno) {
		this.dno = dno;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Employee(int empId, String name) {
		setId(empId);
		setName(name);
	}
	
	public Employee(int id, String name, String job, int mgrId, Date doj,
			double sal, double comm, int dno) {
		super();
		this.id = id;
		this.name = name;
		this.job = job;
		this.mgrId = mgrId;
		this.doj = doj;
		this.sal = sal;
		this.comm = comm;
		this.dno = dno;
	}
	public Employee() {
	
	}
}
