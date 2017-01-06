package com.mansoft.practice.services.intf;

import java.util.List;

import com.mansoft.practice.services.model.Employee;
import com.mansoft.practice.services.model.ExceptionHistory;

public interface EmpServiceIntf {

	boolean addEmployee(Employee employee) throws Exception;

	boolean updateEmployee(Employee employee) throws Exception;

	Employee getEmployee(int empID);

	List<Employee> getAllEmployee();

	boolean addExceptionHistory(ExceptionHistory exceptionHistory)
			throws Exception;

}
