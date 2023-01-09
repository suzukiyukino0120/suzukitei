package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Administrator;
import com.example.domain.Employee;
import com.example.repository.AdministratorMapper;

@Service
@Transactional
public class AdministratorService {

	@Autowired
	private AdministratorMapper administratorMapper;
	
	public void insert(Administrator administrator) {
		administratorMapper.insert(administrator);
	}
	
	public Administrator load(Integer employeeId) {
		if(administratorMapper.load(employeeId).size() == 0) {
			return null;
		}
		
		return administratorMapper.load(employeeId).get(0);
	}
	
	public Administrator findByIdAndPass(Integer employeeId, String pass) {
		if(administratorMapper.findByIdAndPass(employeeId, pass).size() == 0) {
			return null;
		}
		
		return administratorMapper.findByIdAndPass(employeeId, pass).get(0);
	}
}
