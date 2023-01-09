package com.example.repository;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.domain.Plan;
import com.example.domain.Room;

@Mapper
public interface PlanMapper {
	public List<Plan> findPlanWithDate(
			@Param("smoking") Integer smoking, 
			@Param("bathroom") Integer bathroom, 
			@Param("breakfast") Integer breakfast, 
			@Param("dinner") Integer dinner,
			@Param("date") LocalDate date, 
			@Param("stayDays") Integer stayDays, 
			@Param("numOfGuest") Integer numOfGuest);
	
	public List<Plan> findPlanWithoutDate(
			@Param("smoking") Integer smoking, 
			@Param("bathroom") Integer bathroom, 
			@Param("breakfast") Integer breakfast, 
			@Param("dinner") Integer dinner, 
			@Param("planId") Integer planId, 
			@Param("numOfGuest") Integer numOfGuest);
	
	public Plan findCharge(Integer id);
	
	public Plan findGuestCapacity(Integer id);
	
	public List<Plan> findAll();
	
	public Plan findById(Integer id);
	
	public void insert(Plan plan);
	
	public void update(Plan plan);
	
	public void delete(Integer planId);
	
	
}
