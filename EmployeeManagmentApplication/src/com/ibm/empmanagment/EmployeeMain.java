package com.ibm.empmanagment;
import java.util.Scanner;

public class EmployeeMain {
	

	public static void menu() {
        System.out.println("****************Welcome To Employee Managment System *********** \n1. Add Employee \n2.View Employee\n3.Update Employee\n4. Delete Employee\n5.View All Employee\n6.Print Statistic Employee\n7 Exist ");
    }
	  static boolean ordering = true;
	 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		
		EmployeeService empservice=new EmployeeService();
		
       do {
    	 menu();
		System.out.println("Enter choice :");
		int num=sc.nextInt();
		switch(num) {
		
		case 1:
			System.out.println("Add Employee");
			empservice.addEmp();
			
			break;
		case 2:
			System.out.println("View Employee");
			empservice.viewEmp();
			
			break;
		case 3:
			System.out.println("Update Employee");
			empservice.updateEmployee();
			break;
		case 4:
			System.out.println("Delete Employee");
			empservice.deleteEmp();;
			break;
		case 5:
			System.out.println("View All Employee");
			empservice.viewAllEmployees();
			break;
		case 6:
			System.out.println("Print Statistic Employee");
			empservice.printStatistics();
			break;
			
		case 7:
			System.out.println("Thank You !!! ");
			System.exit(0);
			break;
	   default:
		   System.out.println("invalid option .");
		
		}
       }while(ordering);

       sc.close();
	}
	
 
	
}
