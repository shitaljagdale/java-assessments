package com.ibm.empmanagment;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class EmployeeService implements Serializable {
	Scanner sc=new Scanner(System.in);
	
	Employee emp=new Employee();
	TreeSet<Employee> empset=new TreeSet<Employee>();
//	= LocalDate.of(1996, Month.JULY, 21);  //Birth date
//	 System.out.println(birthday);
	
	Employee emp1=new Employee(100,"jhon",23,"Devloper","IT", 40000,LocalDate.of(1996, Month.JULY, 21));
	Employee emp2=new Employee(101,"bob",25,"Manager","CO", 90000,LocalDate.of(1997, Month.JULY, 9));
	Employee emp3=new Employee(102,"sameer",29,"Tester","testing", 50000,LocalDate.of(1999, Month.JULY, 8));
	Employee emp4=new Employee(103,"mishti",34,"Devloper","CO", 55000,LocalDate.of(1993, Month.JULY, 21));
	Employee emp5=new Employee(104,"pari",37,"Tester","testing", 50400,LocalDate.of(1992, Month.JULY, 21));
	Employee emp6=new Employee(105,"gulshan",39,"Tester","testing", 54400,LocalDate.of(1996, Month.JULY, 21));
	
	
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
	
	 String desiganation;
	 String department;
	 double salary;
	// LocalDate DOB ;
	 String DOB;
	 int age;
	 
	 private boolean validate( Predicate<Employee> validator,Employee emp) {
		return  validator.test(emp);
			
		}

	 public static void handleErrMsg(String errMsg,Consumer<String> consumer) {
		 consumer.accept(errMsg);
	 }
	public void addEmp() {
		
		LocalDate today = LocalDate.now();      
	    LocalDate DOB=LocalDate.of(1996, Month.JULY, 21);
	   // System.out.println("Enter a DOB");
	    //LocalDate DOB=sc.next();
		System.out.println("Enter Emp id");
	    id=sc.nextInt();
	    System.out.print("Enter Emp name :");
	     name=sc.next();
	    // System.out.print("Enter Emp age :");
	   // age=sc.nextInt();
	     Period age1 = Period.between(DOB, today);
	    age= age1.getYears();
	    emp.setAge(age);
	    System.out.print("Enter Emp desiganation :");
		desiganation=sc.next();
		System.out.print("Enter Emp department :");
		department=sc.next();
		System.out.print("Enter Emp salary :");
		salary=sc.nextDouble();
		System.out.println("Enter date of birth");
		
		
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
					 handleErrMsg("invalid age",msg->{
						System.out.println("Error Occured "+msg);
						 System.out.println(msg);
					 });
				 }
				 
				 
		
		Employee e1=new Employee(id,name,age,desiganation,department,salary,DOB);
		empset.add(e1);
		System.out.println("Employee e1"+e1);
		
	}
	
	 public void viewEmp() {
		//System.out.print("Enter id to view emp : ");
	
		id=sc.nextInt();
		//System.out.println(empset.contains(id));
		
		for(Employee emps:empset) {
			if( emps.getId()==id) {
				System.out.println(emps);
			}
		}
		System.out.println("Given Employee id is not Exist");
		}
	
	public void viewAllEmployees() {
		System.out.format("Id\tName\tage\tdesiganation\tdepartment\tsalary\tDOB\n");
		for(Employee emps:empset) {
			//System.out.println(emps);
			//System.out.format("Id \t Name \t age \t desiganation \t department \t salary\n");
			System.out.format("%d\t%s\t%d\t%s\t\t%s\t%.2f\t%s\n", emps.getId(),emps.getName(),emps.getAge(),emps.getDesiganation(), emps.getDepartment(),emps.getSalary(), emps.getDOB());

		}
	}
	public void updateEmployee() {
		System.out.print("Enter employee id : ");
		
		id=sc.nextInt();
		for(Employee emps:empset) {
			if(emp.getId()==id) {
				System.out.println("In");
				emps.setName("Updated name");
				//((Employee) emps).setAge(44);
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
		
		for(Employee emps:empset) {
			if(emps.getId()==id) {
				deleteThat=emps;
			}
		}
		if(deleteThat!=null) {
			empset.remove(deleteThat);
		}
		System.out.println("Employee Deleted Successfully...");
		
	}
	 
	public void exportEmp() {
		
		/* FileWriter writer;
		try {
			writer = new FileWriter("C:\\Users\\ShitalBJadhav\\Desktop\\javagit\\ClassRoom-trainning-Java-Programs\\EmployeeManagmentApplication(lambda expresion)\\src\\exportdata.txt");
			
			writer.write(empset.toString());
			BufferedWriter out = new BufferedWriter(writer) ;

             out.close();
             System.out.println("File created successfuly");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
		synchronized (this) {
			OutputStream ops = null;
	        ObjectOutputStream objOps = null;
	        try {
	            ops = new FileOutputStream("C:\\\\Users\\\\ShitalBJadhav\\\\Desktop\\\\javagit\\\\ClassRoom-trainning-Java-Programs\\\\EmployeeManagmentApplication(lambda expresion)\\\\src\\\\exportdata.txt");
	            objOps = new ObjectOutputStream(ops);
	            objOps.writeObject(empset);
	            System.out.println("Employees saved in file ....");
	            objOps.flush();
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally{
	            try{
	                if(objOps != null) objOps.close();
	            } catch (Exception ex){
	                 
	            }
	        }
		}
	 }
    public void importEmp() {
    	 InputStream fileIs = null;
         ObjectInputStream objIs = null;
         try {
             fileIs = new FileInputStream("C:\\\\Users\\\\ShitalBJadhav\\\\Desktop\\\\javagit\\\\ClassRoom-trainning-Java-Programs\\\\EmployeeManagmentApplication(lambda expresion)\\\\src\\\\exportdata.txt");
             objIs = new ObjectInputStream(fileIs);
            //Employee emp = (Employee) objIs.readObject();
             TreeSet<Employee> emp =  (TreeSet)objIs.readObject();
             System.out.println(emp.size()+" Employee Imported From File..");
             for(Employee employee:emp) {
            	 System.out.println(employee);
             }
        
           System.out.println("Import Successfully....");
            
             
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         } finally {
             try {
                 if(objIs != null) objIs.close();
             } catch (Exception ex){
                  
             }
         }
	 }
	public void printStatistics() {
		
		List<Employee> empagegreaterthan30=empset.stream().filter(emp->emp.getAge()>30).collect(Collectors.toList());
		long empagegreaterthan30cnt=empset.stream().filter(emp->emp.getAge()>30).count();
	    System.out.println(empagegreaterthan30);
	  
	    System.out.println("Count of Employees whose age greater than 30 :"+empagegreaterthan30cnt);
		
	    List<Integer> empidlist=empset.stream().filter(emp->emp.getAge()>30).map(emp->emp.getId()).collect(Collectors.toList());
	    System.out.println("List employee id older than 30 year"+empidlist);
	    
	   Map<String,Long> countbydept=
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
	 
	   // countbydept.entrySet().stream().sorted()
	     /*	int count=0;
	      * 
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
		
		*/
	}
}
