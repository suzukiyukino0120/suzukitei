package com.example.form;

import javax.validation.constraints.NotBlank;

public class SearchReservableRoomForm {
	
	@NotBlank(message="年月を選択してください")
	 String month;

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	
	

}
