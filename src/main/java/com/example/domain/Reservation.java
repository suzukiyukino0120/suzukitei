package com.example.domain;

import java.time.LocalDate;

public class Reservation {
	private Integer id;
	
	private Integer planId;
	
	private String name;
	
	private String kana;
	
	private String zipcode;
	
	private String address;
	
	private String telephone;
	
	private String email;
	
	private LocalDate checkinDate;

	private Integer stayDays;
	
	private Integer numOfGuest;
	
	private Integer payMethod;
	
	private Integer totalPrice;
	
	public int calcTotalPrice(int numOfGuest, 
							  int stayDays, 
							  int basicCharge, 
							  int additionalCharge) {
		
		return (basicCharge + additionalCharge * (numOfGuest - 1)) * stayDays;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKana() {
		return kana;
	}

	public void setKana(String kana) {
		this.kana = kana;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getCheckinDate() {
		return checkinDate;
	}

	public void setCheckinDate(LocalDate checkinDate) {
		this.checkinDate = checkinDate;
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

	public Integer getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(Integer payMethod) {
		this.payMethod = payMethod;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "Booking [id=" + id + ", planId=" + planId + ", name=" + name + ", kana=" + kana + ", zipcode=" + zipcode
				+ ", address=" + address + ", telephone=" + telephone + ", email=" + email + ", checkinDate="
				+ checkinDate + ", stayDays=" + stayDays + ", numOfGuest=" + numOfGuest + ", payMethod=" + payMethod
				+ ", totalPrice=" + totalPrice + "]";
	}
	
	

	
}
