package com.mansoft.practice.services.reposiotry;



import java.util.List;

import javax.transaction.Transactional;

import com.mansoft.practice.services.model.Employee;

@Transactional
public interface EmployeeDao extends BaseRepository<Employee, Integer>{
	List<Employee> findAll();
}
