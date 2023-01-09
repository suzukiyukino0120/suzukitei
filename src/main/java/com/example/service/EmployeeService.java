package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Employee;
import com.example.repository.EmployeeMapper;

@Service
@Transactional
public class EmployeeService {

	
	@Autowired
	private EmployeeMapper employeeMapper;
	
	public Employee load(Integer id) {
		if(employeeMapper.load(id).size() == 0) {
			return null;
		}
		return employeeMapper.load(id).get(0);
	}
}
