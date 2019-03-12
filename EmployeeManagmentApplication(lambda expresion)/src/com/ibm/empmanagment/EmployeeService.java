package com.ibm.empmanagment;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.HashMap;

public class EmployeeService {
	Scanner sc=new Scanner(System.in);
	
	Employee emp=new Employee();
	TreeSet<Employee> empset=new TreeSet<Employee>();
	
	Employee emp1=new Employee(100,"jhon",23,"Devloper","IT", 40000);
	Employee emp2=new Employee(101,"bob",25,"Manager","CO", 90000);
	Employee emp3=new Employee(102,"sameer",29,"Tester","testing", 50000);
	Employee emp4=new Employee(103,"mishti",34,"Devloper","CO", 55000);
	Employee emp5=new Employee(104,"pari",37,"Tester","testing", 50400);
	Employee emp6=new Employee(105,"gulshan",39,"Tester","testing", 54400);
	
	
	public EmployeeService() {
		// TODO Auto-generated constructor stub
		empset.add(emp1);
		empset.add(emp2);
		empset.add(emp3);
		empset.add(emp4);
		empset.add(emp5);
		empset.add(emp6);
	}
	
	 int id;
	 String name;
	 int age;
	 String desiganation;
	 String department;
	 double salary;
	 
	 private boolean validate( ValidateEmployee validator,Employee emp) {
			
			return validator.check(emp);
		}

	public void addEmp() {
		
		System.out.println("Enter Emp id");
		id=sc.nextInt();
		
	     System.out.print("Enter Emp name :");
	     name=sc.next();
	    System.out.print("Enter Emp age :");
	    age=sc.nextInt();
	    emp.setAge(age);
	     System.out.print("Enter Emp salary :");
	   salary=sc.nextDouble();
	    System.out.print("Enter Emp desiganation :");
		desiganation=sc.next();
		System.out.print("Enter Emp department :");
		department=sc.next();
		
		// validation using annonymous inner class
		/* boolean status= validate(new  ValidateEmployee() {
			  public boolean check(Employee emp) {
				  return emp.getAge() > 18 && emp.getSalary()>20000 ;
				  
			  }
		  },emp);
		*/
		//validation using lambda expression
		boolean status=validate(emp-> emp.getAge()>0,emp);
		 if(status) {
			 System.out.println("Employee added successfully...");
		 }
		 else {
			 System.out.println("invalid emp details");
		 }
		 
		
		Employee e1=new Employee(id,name,age,desiganation,department,salary);
		empset.add(e1);
		
	}
	
	

	public void viewEmp() {
		//System.out.print("Enter id to view emp : ");
	
		id=sc.nextInt();
		//System.out.println(empset.contains(id));
		
		for(Object emps:empset) {
			if(((Employee) emps).getId()==id) {
				System.out.println(emps);
			}
		}
		System.out.println("Given Employee id is not Exist");
		}
	
	public void viewAllEmployees() {
		System.out.format("Id\tName\tage\tdesiganation\tdepartment\tsalary\n");
		for(Object emps:empset) {
			//System.out.println(emps);
			//System.out.format("Id \t Name \t age \t desiganation \t department \t salary\n");
			System.out.format("%d\t%s\t%d\t%s\t\t%s\t%.2f\n",((Employee) emps).getId(),((Employee) emps).getName(),((Employee) emps).getAge(),((Employee) emps).getDesiganation(),((Employee) emps).getDepartment(),((Employee) emps).getSalary());

		}
	}
	public void updateEmployee() {
		System.out.print("Enter employee id : ");
		
		id=sc.nextInt();
		for(Object emps:empset) {
			if(((Employee) emps).getId()==id) {
				System.out.println("In");
				((Employee) emps).setName("Updated name");
				((Employee) emps).setAge(44);
				System.out.println(emps);
			}
			
		}
		System.out.println("Given Employee id is not Exist");
		
		
  }
	/*Just iterating and removing while doing so, will result in a Concurrent Modification Exception.
	 *  You could temp save the item to remove and remove it later
	 */
	
	public void deleteEmp() {
		Employee deleteThat = null;
		System.out.print("Enter employee id : ");
		id=sc.nextInt();
		
		for(Object emps:empset) {
			if(((Employee) emps).getId()==id) {
				deleteThat=(Employee) emps;
			}
		}
		if(deleteThat!=null) {
			empset.remove(deleteThat);
		}
		System.out.println("Employee Deleted Successfully...");
		
	}
	public void printStatistics() {
		int count=0;
		int testingCount=0;
		int CoCount=0;
		int ItCount=0;
		double testingAge=0;
		double coAge=0;
		double itAge=0;
		boolean matches;
		
		ArrayList<Integer> empids=new ArrayList<Integer>();
		HashMap<String, Integer> empCountByDept=new HashMap<String, Integer>();
		HashMap<String, Object> avgEnmpAgeByDept=new HashMap<String, Object>();
		ArrayList<Employee> empIntestingDept=new ArrayList<Employee>();
		ArrayList<Object> empNameStartWith=new ArrayList<Object>();
		ArrayList<String> deptMoreThan3Emp=new ArrayList<String>();
		
	
		for(Object emps:empset) {
			
			//name start with 's'
			String regex =  "s.*";
            matches = Pattern.matches(regex,((Employee) emps).getName() );
			//System.out.println("matches"+matches);
		
			if(matches==true) {
				empNameStartWith.add(emps);
			}
			
			
			if(((Employee) emps).getAge()>30) {
				count++;
				empids.add(((Employee) emps).getId());
			}
			
			if(((Employee) emps).getDepartment()=="testing") {
				testingCount++;
				testingAge=testingAge+((Employee) emps).getAge();
				//show emp whose dept is testing
				empIntestingDept.add((Employee) emps);
				
				//System.out.println("employee is testing department "+emps);
			}
			if(((Employee) emps).getDepartment()=="CO") {
				CoCount++;
				coAge=coAge+((Employee) emps).getAge();
			}
			if(((Employee) emps).getDepartment()=="IT") {
				ItCount++;
				itAge=itAge+((Employee) emps).getAge();
			}
		}
		
		
		System.out.println("Number of employees older than 30 age are : "+count);
		System.out.println();
		
		System.out.println("Number of employees older than 30 age array is  : "+empids);
		System.out.println();
		
		empCountByDept.put("testing", testingCount);
		empCountByDept.put("CO", CoCount);
		empCountByDept.put("IT", ItCount);
		System.out.println("Employee count by department : ");
		System.out.println(empCountByDept.entrySet());
		System.out.println();
		
		avgEnmpAgeByDept.put("testing", testingAge/testingCount);
		avgEnmpAgeByDept.put("CO", coAge/CoCount);
		avgEnmpAgeByDept.put("IT", itAge/ItCount);
		System.out.println("Avg Emp age by department : ");
		System.out.println(avgEnmpAgeByDept.entrySet());
		System.out.println();
		
		System.out.println("Employees whose Name Start with 's' are :");
		for(Object emp:empNameStartWith) {
			System.out.println(emp);
		}
		System.out.println();
		//emp whose dept is testing
		System.out.println("Employees in Testing Department are :");
		for(Employee empInTestingDept:empIntestingDept) {
			System.out.println(empInTestingDept);
		}
		System.out.println();
		
		//dept more than 3 emp
		if(testingCount>=3) {
			deptMoreThan3Emp.add("Testing");
		}
		if(CoCount>=3) {
			deptMoreThan3Emp.add("CO");
		}
		if(ItCount>=3) {
			deptMoreThan3Emp.add("IT");
		}
		System.out.println("Department having more than 3 emp :" +deptMoreThan3Emp);
		
		
	}
}
