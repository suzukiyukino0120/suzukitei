package com.example.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Reservation;

@Mapper
public interface ReservationMapper {
	
	public void insert(Reservation booking);
	
	public List<Reservation> find(Reservation reservation);
	
	public Reservation findById(Integer id);

	public void delete(Integer id);
	
}
