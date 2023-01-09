package com.example.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Reservation;
import com.example.domain.ReservationCalender;
import com.example.domain.Plan;
import com.example.repository.ReservationMapper;
import com.example.repository.PlanMapper;
import com.example.repository.ReservationCalenderMapper;

@Service
@Transactional
public class ReservationService {
	
	@Autowired
	private ReservationMapper reservationMapper;
	
	@Autowired
	private ReservationCalenderMapper reservationCalenderMapper;

	@Autowired
	private PlanMapper planMapper;
	
	
	public void insert(Reservation reservation) {
		reservationMapper.insert(reservation);
	}
	
	public Plan checkGuestCapacity(Integer planId) {
		return planMapper.findGuestCapacity(planId);
		}
	
	public Plan checkCharge(Integer planId) {
		return planMapper.findCharge(planId);
	}
	
	public String checkFullReservation(LocalDate startDate, LocalDate endDate, Integer roomId) {
		List<ReservationCalender> reservationCalender 
		= reservationCalenderMapper.findReservableRoomById(startDate, endDate, roomId);
		
		String fullReservationMsg = "";//満室時のエラー文　
		for(ReservationCalender day: reservationCalender) {
			if(day.getReservedRoom() >= day.getReservationLimit()) {
				fullReservationMsg += day.getDate()+ "  ";
			}
		}
		return fullReservationMsg;
	}
}
