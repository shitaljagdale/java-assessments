package com.ibm.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.ibm.jdbc.JdbcConnection;
import com.ibm.model.Employee;

public class EmployeeDaoImpl implements EmployeeDaoInterface {
	
	//Connection connection = DBUtil.getConnection();
	//Connection connection=null;
	//PreparedStatement pstmt= null;
    Scanner sc=new Scanner(System.in);
    TreeSet<Employee> empset = new TreeSet<>();
    int id;
    String name;
    int age;
    String department;
    String designation;
    double salary;
    
    Connection connection = null;
    PreparedStatement pstmt = null;
    Employee emp=new Employee();
    
    private boolean validate( ValidateEmployee validator,Employee emp) {
		
		return validator.check(emp);
	}
    
	@Override
	public void createEmployee(Employee employee) {
		String createemp = ("insert into employee(name,age,designation,department,salary) values(?,?,?,?,?)");
		try {
			connection=JdbcConnection.getConnection();
			
			// pstmt = connection.prepareStatement(createemp);
			
			System.out.print("Enter Employee Name : ");
			name=sc.next();
			System.out.print("Enter Employee age : ");
			age=sc.nextInt();
			System.out.print("Enter Employee designation : ");
			designation=sc.next();
			System.out.print("Enter Employee department : ");
			department=sc.next();
			System.out.print("Enter Employee salary : ");
			salary=sc.nextDouble();
			 pstmt = connection.prepareStatement(createemp);
			//validation using lambda expression
			boolean status=validate(emp-> emp.getAge()>0,emp);
			 if(status) {
				 System.out.println("Employee added successfully...");
				 pstmt.setString(1, name);
			        pstmt.setInt(2, age);
			        pstmt.setString(3, designation);
			        pstmt.setString(4, department);
			        pstmt.setDouble(5, salary);
		            int insertCount = pstmt.executeUpdate();
					System.out.println(insertCount+" Employee inserted successfully...");
			 }
			 else {
				 System.out.println("invalid emp details");
			 }
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Employee getEmployeeById(Integer employeeId) {
		Employee employee = new Employee();
		
		String getempbyid = "SELECT *FROM employee WHERE id=?";
		try {
			connection=JdbcConnection.getConnection();
			pstmt = connection.prepareStatement(getempbyid);
			pstmt.setInt(1, employeeId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				id = rs.getInt("id");
				name = rs.getString("name");
				 age = rs.getInt("age");
				 designation=rs.getString("designation");
				 department=rs.getString("department");
			    salary = rs.getDouble("salary");
			
				employee.setId(id);
				employee.setName(name);
				employee.setAge(age);
				employee.setDesignation(designation);
				employee.setDepartment(department);
				employee.setSalary(salary);
				
				empset.add(employee);
				
				
			}
		 }
		catch(SQLException e) {
			e.printStackTrace();
		}
		for(Employee emp:empset) {
			if(emp.getId()==employeeId) {
				System.out.println(emp);
			}
		}
		
		// TODO Auto-generated method stub
		return employee;
	}

	

	@Override
	public void deleteEmployeeById(Integer employeeId) {
		// TODO Auto-generated method stub
		String deletbyid = "DELETE FROM employee WHERE id=?";
		try{
			connection = JdbcConnection.getConnection();
			pstmt = connection.prepareStatement(deletbyid);
			
            pstmt.setInt(1,employeeId);
			
			int executeUpdate = pstmt.executeUpdate();
			
			if(executeUpdate == 1){
				 System.out.println("Employee is deleted with ID::"+employeeId);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public TreeSet<Employee> getAllEmployeesInfo() {
		// TODO Auto-generated method stu
		String viewemp = "SELECT *FROM employee";
		TreeSet<Employee> empset = new TreeSet<>();
		try{
			connection = JdbcConnection.getConnection();
			pstmt = connection.prepareStatement(viewemp);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				id = rs.getInt("id");
				name = rs.getString("name");
				 age = rs.getInt("age");
				 designation=rs.getString("designation");
				 department=rs.getString("department");
			    salary = rs.getDouble("salary");
				
				
				Employee employee = new Employee();
				employee.setId(id);
				employee.setName(name);
				employee.setAge(age);
				employee.setDesignation(designation);
				employee.setDepartment(department);
				employee.setSalary(salary);
				
				empset.add(employee);
				
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.format("ID\tName\tAge\tDesignation\tDepartment\tSalary\n");
		for(Employee emp:empset) {
			  System.out.format("%d\t%s\t%d\t%s\t%s\t%f\n",emp.getId(),emp.getName(),emp.getAge(),emp.getDesignation(),emp.getDepartment(),emp.getSalary());
		}
		return empset;
	
     }

	@Override
	public void updateEmployeeNameById(String newName, Integer employeeId) {
		// TODO Auto-generated method stub
		String updatename = "UPDATE employee set name=? WHERE id=?";
		try {
			connection=JdbcConnection.getConnection();
			pstmt=connection.prepareStatement(updatename);
			pstmt.setString(1, newName);
			pstmt.setInt(2,employeeId);
			
			int executeUpdate = pstmt.executeUpdate();
			
			if(executeUpdate ==1){
				System.out.println("Employee name is updated..");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void exportDataInFile() {
		// TODO Auto-generated method stub
		String allemp = "SELECT *FROM employee";
		List<String> employees=new ArrayList<>();
		synchronized (this) {
	
	 	try{
			connection = JdbcConnection.getConnection();
			pstmt = connection.prepareStatement(allemp);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				id = rs.getInt("id");
				name = rs.getString("name");
				 age = rs.getInt("age");
				 designation=rs.getString("designation");
				 department=rs.getString("department");
			    salary = rs.getDouble("salary");
	    
			    employees.add(id + " " + name + " " + age + " " + designation+" "+department+" "+salary);
			  }
			 BufferedWriter out = null;
	            try {
	                    File file = new File("data.txt");
	                    out = new BufferedWriter(new FileWriter(file, true));
	                    for (Object s : employees) {
	                    	out.write((String) s);
	                            out.newLine();

	                    }
	                    out.close();
	            } catch (IOException e) {
	            }
	    
            rs.close();
            pstmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		
	}

	@Override
	public void importDatafromFile() {
		try {
	         File file = new File("data.txt");
	         Scanner scanner = new Scanner(file);
	        while (scanner.hasNextLine()) {
	            //System.out.println(scanner.nextLine());
	        	String s=scanner.nextLine();
	        	String[] arr=s.split(" ");
	        	//.out.println(arr.length);
	        	System.out.println("ID="+arr[0]+ " , name="+arr[1]+" , Age="+arr[2]+" , Designation="+arr[3]+" , department="+arr[4]+" ,salary="+arr[5]);
	        	
	         }
	        
	        
	        
	       } catch (FileNotFoundException e) {
	         e.printStackTrace();
	       }
		
		
		
	}

	@Override
	public void printStatistic() {
		String viewemp = "SELECT *FROM employee";
		TreeSet<Employee> empset = new TreeSet<>();
		try{
			connection = JdbcConnection.getConnection();
			pstmt = connection.prepareStatement(viewemp);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				id = rs.getInt("id");
				name = rs.getString("name");
				 age = rs.getInt("age");
				 designation=rs.getString("designation");
				 department=rs.getString("department");
			    salary = rs.getDouble("salary");
				
				
				Employee employee = new Employee();
				employee.setId(id);
				employee.setName(name);
				employee.setAge(age);
				employee.setDesignation(designation);
				employee.setDepartment(department);
				employee.setSalary(salary);
				
				empset.add(employee);
			}
			List<Employee> empagegreaterthan30=empset.stream().filter(emp->emp.getAge()>30).collect(Collectors.toList());
			long empagegreaterthan30cnt=empset.stream().filter(emp->emp.getAge()>30).count();
		    
			System.out.println("Number of employee older than 30 are : "+empagegreaterthan30cnt);
			System.out.println("Employee older than 30 :");
		    for(Employee emp:empagegreaterthan30) {
		    	System.out.println(emp);
		    }
		    
		    List<Integer> empidlist=empset.stream().filter(emp->emp.getAge()>30).map(emp->emp.getId()).collect(Collectors.toList());
		    System.out.println("List employee id older than 30 year"+empidlist);
		    
		    Map<String, Long> countbydept=
				    empset.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.counting()));
			   System.out.println("Department wise employee count");
			  System.out.println(countbydept);
			  
			 Map<String,Double> avgSalryByDept=
				 empset.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.averagingDouble(Employee::getSalary)));
				  System.out.println("Avrag salary by department");
				System.out.println(avgSalryByDept);
						  
			DoubleSummaryStatistics ll= empset.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
			 System.out.println("Sum of Sal"+ll);
					     
			 List<String> dept= countbydept.entrySet().stream().filter(e->e.getValue()>2).map(Map.Entry::getKey).collect(Collectors.toList());
			 System.out.println("Department who has more than 2 employee "+dept);
					     
			 List<Employee> emps=   empset.stream().filter(emp->emp.getName().startsWith("s")).collect(Collectors.toList());
		     System.out.println(emps);
					   
		    Map<String, Long> map=new TreeMap<>();
			 map=empset.stream().sorted(Comparator.comparing(Employee::getDepartment)).collect(Collectors.groupingBy(Employee::getDepartment,TreeMap::new,Collectors.counting()));
			System.out.println("Sorting" + map);
					 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
		
	}

	
	
	


