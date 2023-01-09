package com.example.domain;

import java.time.LocalDate;


public class ReservationCalender {
	
	private LocalDate date;
	
	private Integer roomId;
	
	private Integer reservedRoom;
	
	private Integer reservationLimit;
	
	private String state;

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}


	public Integer getReservedRoom() {
		return reservedRoom;
	}

	public void setReservedRoom(Integer reservedRoom) {
		this.reservedRoom = reservedRoom;
	}


	public Integer getReservationLimit() {
		return reservationLimit;
	}

	public void setReservationLimit(Integer reservationLimit) {
		this.reservationLimit = reservationLimit;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public ReservationCalender clone(ReservationCalender reservationCalender) {
		ReservationCalender newReservationCalender = new ReservationCalender();
		newReservationCalender.setDate(reservationCalender.getDate());
		newReservationCalender.setReservationLimit(reservationCalender.getReservationLimit());
		newReservationCalender.setReservedRoom(reservationCalender.getReservedRoom());
		return newReservationCalender;
	}

	@Override
	public String toString() {
		return "ReservationCalender [date=" + date + ", roomId=" + roomId + ", reservedRoom=" + reservedRoom
				+ ", reservationLimit=" + reservationLimit + ", state=" + state + "]";
	}



	
	
	
	
	
	

}
