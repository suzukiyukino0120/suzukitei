package com.example.domain;

import java.util.List;

public class Room {
	
	private Integer id;
	
	private Integer smoking;
	
	private Integer bathroom;
	
	
	private Integer guestCapacity;
	
	
	private List<ReservationCalender> reservationCalender;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSmoking() {
		return smoking;
	}

	public void setSmoking(Integer smoking) {
		this.smoking = smoking;
	}

	public Integer getBathroom() {
		return bathroom;
	}

	public void setBathroom(Integer bathroom) {
		this.bathroom = bathroom;
	}



	public Integer getGuestCapacity() {
		return guestCapacity;
	}

	public void setGuestCapacity(Integer guestCapacity) {
		this.guestCapacity = guestCapacity;
	}

	public List<ReservationCalender> getReservationCalender() {
		return reservationCalender;
	}

	public void setReservationCalender(List<ReservationCalender> reservationCalender) {
		this.reservationCalender = reservationCalender;
	}

	@Override
	public String toString() {
		return "Room [id=" + id + ", smoking=" + smoking + ", bathroom=" + bathroom + ", guestCapacity=" + guestCapacity
				+ ", reservationCalender=" + reservationCalender + "]";
	}


	
	


	
	

}
