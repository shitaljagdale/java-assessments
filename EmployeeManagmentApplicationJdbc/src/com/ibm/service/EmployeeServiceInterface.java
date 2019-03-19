package com.ibm.service;

import java.util.List;
import java.util.TreeSet;

import com.ibm.model.Employee;

public interface EmployeeServiceInterface {
	public abstract void createEmployee(Employee employee);
	public abstract Employee getEmployeeById(Integer employeeId);
	public abstract void updateEmployeeNameById(String newName,Integer employeeId);
	public abstract void deleteEmployeeById(Integer employeeId);
	public abstract TreeSet<Employee> getAllEmployeesInfo();
	public abstract void exportDataInFile();
	public abstract void importDatafromFile();
	public abstract void printStatistic();

}
