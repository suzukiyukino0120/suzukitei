package com.example.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.domain.Administrator;

@Mapper
public interface AdministratorMapper {
	public void insert(Administrator administrator);
	
	public List<Administrator> load(Integer employeeId);
	
	public List<Administrator> findByIdAndPass(
												@Param("employeeId") Integer employeeId, 
												@Param("password") String password);
		
	

}
