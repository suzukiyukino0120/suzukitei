package com.example.controller;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.ReservationCalender;
import com.example.domain.Plan;
import com.example.form.SearchReservableRoomForm;
import com.example.service.ReservationCalenderService;

@Controller
@RequestMapping("/reservableRoom")
public class ReservationCalenderController {
	
	@ModelAttribute
	public SearchReservableRoomForm setUpSearchRoomForm() {
		return new SearchReservableRoomForm();
	}
	
	@Autowired 
	private HttpSession session;
	
	@Autowired
	private ReservationCalenderService reservationCalenderService;
	
	@RequestMapping("/toSearchReservableRoom")
	public String toSearchRoom(Integer planListNum) {
		
		session.removeAttribute("reservationCalender");
		
		List<Plan> planList = (List<Plan>) session.getAttribute("planList");
		
		session.setAttribute("plan", planList.get(planListNum));
		
		return "search_reservable_room";
	}
	
	@RequestMapping("/searchReservableRoom")
	public String searchEmptyRoom(@Validated SearchReservableRoomForm form, BindingResult result,  Model model){
		
		if(result.hasErrors()) {
			return"search_reservable_room";
		}
		
		String strStartOfMonth = form.getMonth() +"-01";
		LocalDate startOfMonth = LocalDate.parse(strStartOfMonth, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	
		LocalDate endOfMonth = startOfMonth.withDayOfMonth(startOfMonth.lengthOfMonth());
		
		Plan plan = (Plan) session.getAttribute("plan");

		List<ReservationCalender> reservationCalender 
			= reservationCalenderService.searchReservedRoom(startOfMonth, endOfMonth, plan.getRoomId());
	
		session.setAttribute("reservationCalender", reservationCalender); 
		return "search_reservable_room";
		
	}

}
