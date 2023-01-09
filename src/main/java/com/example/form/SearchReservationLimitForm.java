package com.example.form;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class SearchReservationLimitForm {
	@NotNull(message="始まりの日付を入力してください")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	LocalDate startDate;
	
	@NotNull(message="終わりの日付を入力してください")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	LocalDate endDate;
	

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	
	

	@Override
	public String toString() {
		return "SearchEmptyRoomForm [startDate=" + startDate + ", endDate=" + endDate +"]";
	}


}
