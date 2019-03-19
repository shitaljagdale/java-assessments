package com.ibm.controller;

import java.util.Scanner;
import java.util.TreeSet;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.ibm.model.Employee;
import com.ibm.service.EmployeeServiceImpl;
import com.ibm.service.EmployeeServiceInterface;

public class EmployeeController {
	
	public static void menu() {
		System.out.println("Welcome to employee managment application \n1.Add Employee\n2.View Employee\n3.Update Employee\n4.Delete Employee\n5.View All Employee\n6.Print Statistic\n7.Import\n8.Export\n9.Exist\n");
      }
	static boolean ordering=true;
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		EmployeeServiceInterface service=new EmployeeServiceImpl();
		Employee emp=new Employee(100,"shital",22,"devloper","IT",30000.0);
		TreeSet<Employee> empset=new TreeSet<>();
		empset.add(emp);
		Integer empid;
		

		do{
			menu();
			System.out.print("Enter choice :");
			int choice=sc.nextInt();
			switch(choice) {
			
		   case 1:
				System.out.println("Add Employee");
				service.createEmployee(emp);
				break;
			
		   case 2:
			   System.out.println("View Employee by id");
			   System.out.print("Enter Employee id: ");
			    empid=sc.nextInt();
			   service.getEmployeeById(empid);
			   break;
		 
	      case 3:
		       System.out.println("update Employee");
		       System.out.print("Enter emp new name ");
		       String newName=sc.next();
		       System.out.print("Enter Employee id: ");
			    empid=sc.nextInt();
		       service.updateEmployeeNameById(newName, empid);
		       break;
		     
	      case 4:
	    	  System.out.println("delete emp");
	    	  System.out.print("Enter Employee id: ");
			    empid=sc.nextInt();
			    service.deleteEmployeeById(empid);
	    	  break;
	    	  
	      case 5:
	    	  System.out.println("view all emp");
	    	  service.getAllEmployeesInfo();
	    	  break;
	    	  
	      case 6:
	    	  System.out.println("print statistics");
	    	  service.printStatistic();
	    	  break;
	    	  
	      case 7:
	    	  System.out.println("Import");
	    	  Callable<Boolean> importT=new Callable<Boolean>() {

					@Override
					public Boolean call() throws Exception {
						// TODO Auto-generated method stub
						service.importDatafromFile();
						System.out.println(Thread.currentThread().getName());
					   return true;
					}
			   };
				ExecutorService importservice = Executors.newFixedThreadPool(1);
				Future<Boolean> importfuture=importservice.submit(importT);
				System.out.println(importfuture);
				importservice.shutdown();
	    	  break;
	    	  
	      case 8:
	    	  System.out.println("Export to file Employee");
				Callable<Boolean> exportT=new Callable<Boolean>() {

					@Override
					public Boolean call() throws Exception {
						// TODO Auto-generated method stub
						service.exportDataInFile();
						System.out.println(Thread.currentThread().getName());
					   return true;
					}
			   };
				ExecutorService e = Executors.newFixedThreadPool(1);
				Future<Boolean> f=e.submit(exportT);
				System.out.println(f);
				e.shutdown();
				break;
	    	  
	      case 9:
	    	  System.out.println("Thank You !!");
	    	  System.exit(0);
	    	  break;
	
	      default:
	    	  System.out.println("Please enter valid choice....");
		}
		
	}while(ordering);
		
		sc.close();

  }
}
