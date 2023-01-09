package com.example.form;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class SearchPlanForm {
	Integer smoking;
	
	Integer bathroom;
	
	Integer breakfast;
	 
	Integer dinner;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	LocalDate date;
	
	Integer stayDays;
	
	Integer numOfGuest;
	
	Integer planId;

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

	public Integer getBreakfast() {
		return breakfast;
	}

	public void setBreakfast(Integer breakfast) {
		this.breakfast = breakfast;
	}

	public Integer getDinner() {
		return dinner;
	}

	public void setDinner(Integer dinner) {
		this.dinner = dinner;
	}



	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Integer getStayDays() {
		return stayDays;
	}

	public void setStayDays(Integer stayDays) {
		this.stayDays = stayDays;
	}

	public Integer getNumOfGuest() {
		return numOfGuest;
	}

	public void setNumOfGuest(Integer numOfGuest) {
		this.numOfGuest = numOfGuest;
	}
	
	

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	@Override
	public String toString() {
		return "SearchPlanForm [smoking=" + smoking + ", bathroom=" + bathroom + ", breakfast=" + breakfast
				+ ", dinner=" + dinner + ", date=" + date + ", stayDays=" + stayDays + ", numOfGuest=" + numOfGuest
				+ ", planId=" + planId + "]";
	}


	

	

	
	
	
	 
	 

}
