package com.example.form;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

public class ReservationForm {
	
	@NotBlank(message="名前を入力して下さい")
	private String name;
	
	@NotBlank(message="ふりがなを入力して下さい")
	@Pattern(regexp="^[ぁ-んー]*$", message="ひらがなを入力してください")
	private String kana;
	
	@NotBlank(message="郵便番号を入力してください")
	@Pattern(regexp="^[0-9]{7}$", message="郵便番号はハイフンなしの７桁で入力してください")
	private String zipcode;
	
	@NotBlank(message="住所を入力して下さい")
	private String address;
	
	@NotBlank(message="電話番号を入力してください")
	@Pattern(regexp="^[0-9]{9,12}$", message="電話番号はハイフンなしの9桁～12桁で入力してください")
	private String telephone;
	
	@NotBlank(message="メールアドレスを入力して下さい")
	@Email(message="メールアドレスの形式が不正です")
	private String email;
	
	@NotNull(message="チェックインの日付を入力してください")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate checkinDate;
	
	@NotNull(message="宿泊日数を入力してください")
	private Integer stayDays;
	
	@NotNull(message="宿泊人数を入力してください")
	private Integer numOfGuest;
	
	@NotNull(message="お支払方法を選択してください")
	private Integer payMethod;
	
	private Integer planId;
	

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
	
	

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	@Override
	public String toString() {
		return "ReservationForm [name=" + name + ", kana=" + kana + ", zipcode=" + zipcode + ", address=" + address
				+ ", telephone=" + telephone + ", email=" + email + ", checkinDate=" + checkinDate + ", stayDays="
				+ stayDays + ", numOfGuest=" + numOfGuest + ", payMethod=" + payMethod + ", planId=" + planId + "]";
	}



	

}
