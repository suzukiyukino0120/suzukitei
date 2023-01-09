package com.example.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Employee;

@Mapper
public interface EmployeeMapper {
	public List<Employee> load(Integer id); 

}
