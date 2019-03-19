package com.ibm.service;

import java.util.List;
import java.util.TreeSet;

import com.ibm.dao.EmployeeDaoImpl;
import com.ibm.dao.EmployeeDaoInterface;
import com.ibm.model.Employee;

public class EmployeeServiceImpl implements EmployeeServiceInterface{
	
	EmployeeDaoInterface dao=new EmployeeDaoImpl();
   
	

	public EmployeeDaoInterface getDao() {
		return dao;
	}

	public void setDao(EmployeeDaoInterface dao) {
		this.dao = dao;
	}

	@Override
	public void createEmployee(Employee employee) {
		
		// TODO Auto-generated method stub
		dao.createEmployee(employee);
		
	}

	@Override
	public Employee getEmployeeById(Integer employeeId) {
		// TODO Auto-generated method stub
		return dao.getEmployeeById(employeeId);
	}


	@Override
	public void deleteEmployeeById(Integer employeeId) {
		// TODO Auto-generated method stub
		dao.deleteEmployeeById(employeeId);
		
	}

	@Override
	public TreeSet<Employee> getAllEmployeesInfo() {
		// TODO Auto-generated method stub
		
		return dao.getAllEmployeesInfo();
	}

	@Override
	public void updateEmployeeNameById(String newName, Integer employeeId) {
		// TODO Auto-generated method stub
		dao.updateEmployeeNameById(newName, employeeId);
		
	}

	@Override
	public void exportDataInFile() {
		// TODO Auto-generated method stub
		dao.exportDataInFile();
	}

	@Override
	public void importDatafromFile() {
		dao.importDatafromFile();
	}

	@Override
	public void printStatistic() {
		dao.printStatistic();
	}

	

}
