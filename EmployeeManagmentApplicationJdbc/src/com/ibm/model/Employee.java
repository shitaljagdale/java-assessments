package com.ibm.model;

public class Employee implements Comparable<Employee>{
	private int id;
	private String name;
	private int age;
	private String designation;
	private String department;
	private Double salary;
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", age=" + age + ", designation=" + designation
				+ ", department=" + department + ", salary=" + salary + "]";
	}

	public Employee(int id, String name, int age, String designation, String department, Double salary) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.designation = designation;
		this.department = department;
		this.salary = salary;
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}

	@Override
	public int compareTo(Employee obj) {
		// TODO Auto-generated method stub
		if(obj instanceof Employee) {
			return this.getName().compareTo(((Employee) obj).getName());
		}
		return 0;
	}
	
	
	
	

}
