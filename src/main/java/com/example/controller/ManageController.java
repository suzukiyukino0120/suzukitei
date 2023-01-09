package com.example.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Plan;
import com.example.domain.Reservation;
import com.example.domain.ReservationCalender;
import com.example.form.CreatePlanForm;
import com.example.form.SearchReservationForm;
import com.example.form.SearchReservationLimitForm;
import com.example.form.UpdatePlanForm;
import com.example.form.UpdateReservationLimitForm;
import com.example.service.ManageService;
import com.example.validator.UpdatePlanValidator;

@Controller
@RequestMapping("/manage")
public class ManageController {
	@Autowired
	private HttpSession session;
	
	@ModelAttribute
	public CreatePlanForm setUpCreatePlanForm() {
		return new CreatePlanForm();
	}
	
	@ModelAttribute
	public UpdatePlanForm setUpUpdatePlanForm() {
		return new UpdatePlanForm();
	}
	
	@ModelAttribute
	public SearchReservationForm setUpSearchReservationForm() {
		return new SearchReservationForm();
	}
	
	@ModelAttribute
	public SearchReservationLimitForm setUpSearchReservationLimitForm() {
		return new SearchReservationLimitForm();
	} 
	
	@ModelAttribute
	public UpdateReservationLimitForm setUpUpdateReservationLimitForm() {
		return new UpdateReservationLimitForm();
	}
	
	@Autowired
	private ManageService manageService;
	
	@Autowired
	private UpdatePlanValidator updatePlanValidator;
	
	@InitBinder("updatePlanForm")
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(updatePlanValidator);
    }
	
	
	@RequestMapping("/toRegisteredPlanList")
	public String toRegisteredPlanList(Model model) {
		
		model.addAttribute("planList", manageService.findAllPlan());
		
		return"manage/registered_plan_list";
	}
	
	@RequestMapping("/toCreatePlan")
	public String toCreatePlan() {
		return "manage/create_plan";
	}
	
	@RequestMapping("/createPlan")
	public String createPlan(@Validated CreatePlanForm createPlanForm, 
								BindingResult result, Model model) {
		if(result.hasErrors()) {
			return"manage/create_plan";
		}
		
		Plan plan = new Plan();
		plan.setName(createPlanForm.getName());
		plan.setRoomId(createPlanForm.getRoomId());
		plan.setBreakfast(createPlanForm.getBreakfast());
		plan.setDinner(createPlanForm.getDinner());
		plan.setBasicCharge(createPlanForm.getBasicCharge());
		plan.setAdditionalCharge(createPlanForm.getAdditionalCharge());
		plan.setComment(createPlanForm.getComment());

		String imageName = manageService.strageImage(createPlanForm.getImage());
		plan.setImage(imageName);
		
		manageService.insertPlan(plan);
		
		return"redirect:/manage/toRegisteredPlanList";
	}
	
	@RequestMapping("/toUpdatePlan")
	public String toUpdatePlan(Integer id, Model model) {
		
		Plan plan = manageService.findPlanById(id);
		
		//編集フォームに現在の登録内容が入力済になるようにする
		UpdatePlanForm updatePlanForm = new UpdatePlanForm();
		BeanUtils.copyProperties(plan, updatePlanForm);
		updatePlanForm.setNowImage("notChange");
		
		model.addAttribute("updatePlanForm", updatePlanForm);
		session.setAttribute("image", plan.getImage());
		
		return "manage/update_plan";
	}
	
	
	@RequestMapping("/updatePlan")
	public String updatePlan(@Validated UpdatePlanForm updatePlanForm, 
							BindingResult result, Model model) {
		if(result.hasErrors()) {
		System.out.println(result);
			return "manage/update_plan";
		}
				
		Plan plan = new Plan();
		plan.setId(updatePlanForm.getId());
		plan.setName(updatePlanForm.getName());
		plan.setRoomId(updatePlanForm.getRoomId());
		plan.setBreakfast(updatePlanForm.getBreakfast());
		plan.setDinner(updatePlanForm.getDinner());
		plan.setBasicCharge(updatePlanForm.getBasicCharge());
		plan.setAdditionalCharge(updatePlanForm.getAdditionalCharge());
		plan.setComment(updatePlanForm.getComment());
		
		if("notChange".equals(updatePlanForm.getNowImage())) {
			plan.setImage((String) session.getAttribute("image"));
		}else {
			String imageName = manageService.strageImage(updatePlanForm.getImage());
			plan.setImage(imageName);
		}
		
		manageService.updatePlan(plan);
		
		return"redirect:/manage/toRegisteredPlanList";
	}
	
	@RequestMapping("/toDeletePlan")
	public String toDeletePlan(Integer id, Model model) {
		
		model.addAttribute("plan", manageService.findPlanById(id));
		
		return"manage/delete_plan";
	}
	
	@RequestMapping("/deletePlan")
	public String deletePlan(Integer planId) {
		manageService.deletePlan(planId);
		
		return"redirect:/manage/toRegisteredPlanList";
	}
	
	
	@RequestMapping("/toReservationList")
	public String toReservationList(Model model) {
		
		//デフォルトで今日宿泊中の予約を表示
		Reservation reservation = new Reservation();
		reservation.setCheckinDate(LocalDate.now());
	
		model.addAttribute("reservationList", manageService.find(reservation));
		
		return "manage/reservation_list";
	}
	
	@RequestMapping("/searchReservation")
	public String searchReservation(SearchReservationForm searchReservationForm, Model model) {
		
		Reservation reservation = new Reservation();
		BeanUtils.copyProperties(searchReservationForm, reservation);
		
		model.addAttribute("reservationList", manageService.find(reservation));
		
		
		
		return "manage/reservation_list";
	}
	
	@RequestMapping("/toReservationDetail")
	public String toReservationDetail(Integer id, Model model) {
		model.addAttribute("reservation", manageService.findReservationById(id));
		
		return "manage/reservation_detail";
	}
	
	@RequestMapping("/cancelReservation")
	public String cancelReservation(Integer reservationId) {
		manageService.cancelReservation(reservationId);
		
		return"redirect:/manage/toReservationList";
	}

	@RequestMapping("/toReservationLimit")
	public String toReservationLimit() {
		return"manage/reservable_room_manage";
	}
	
	@RequestMapping("/searchReservationLimit")
	public String searchReservationLimit(@Validated SearchReservationLimitForm searchReservationLimitForm, BindingResult result,  Model model) {
		if(result.hasErrors()) {
			return "manage/reservable_room_manage";
		}
		model.addAttribute("reservationCalender", manageService.findAllReservationLimit(searchReservationLimitForm.getStartDate(), searchReservationLimitForm.getEndDate()));
		
		
		return"manage/reservable_room_manage";
	}
	
	@RequestMapping("/toUpdateReservationLimit")
	public String toUpdateReservationLimit(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, Model model) {
		List<ReservationCalender> reservationCalender = manageService.findAllReservationLimit(date, date);
		UpdateReservationLimitForm updateReservationLimitForm = new UpdateReservationLimitForm();
		updateReservationLimitForm.setDate(date);
		updateReservationLimitForm.setLimitOfRoom1(reservationCalender.get(0).getReservationLimit());
		updateReservationLimitForm.setLimitOfRoom2(reservationCalender.get(1).getReservationLimit());
		updateReservationLimitForm.setLimitOfRoom3(reservationCalender.get(2).getReservationLimit());
		updateReservationLimitForm.setLimitOfRoom4(reservationCalender.get(3).getReservationLimit());
		model.addAttribute("updateReservationLimitForm", updateReservationLimitForm);
		
		return"manage/reservable_room_update";
	}
	
	@RequestMapping("/updateReservationLimit")
	public String updateReservationLimit(@Validated UpdateReservationLimitForm updateReservationLimitForm, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return"manage/reservable_room_update";
		}
		
		List<ReservationCalender> reservationCalender = new ArrayList<>();
		
		ReservationCalender calenderOfRoom1 = new ReservationCalender();
		calenderOfRoom1.setRoomId(1);
		calenderOfRoom1.setDate(updateReservationLimitForm.getDate());
		calenderOfRoom1.setReservationLimit(updateReservationLimitForm.getLimitOfRoom1());
		reservationCalender.add(calenderOfRoom1);
		
		ReservationCalender calenderOfRoom2 = new ReservationCalender();
		calenderOfRoom2.setRoomId(2);
		calenderOfRoom2.setDate(updateReservationLimitForm.getDate());
		calenderOfRoom2.setReservationLimit(updateReservationLimitForm.getLimitOfRoom2());
		reservationCalender.add(calenderOfRoom2);
		
		ReservationCalender calenderOfRoom3 = new ReservationCalender();
		calenderOfRoom3.setRoomId(3);
		calenderOfRoom3.setDate(updateReservationLimitForm.getDate());
		calenderOfRoom3.setReservationLimit(updateReservationLimitForm.getLimitOfRoom3());
		reservationCalender.add(calenderOfRoom3);
		
		ReservationCalender calenderOfRoom4 = new ReservationCalender();
		calenderOfRoom4.setRoomId(4);
		calenderOfRoom4.setDate(updateReservationLimitForm.getDate());
		calenderOfRoom4.setReservationLimit(updateReservationLimitForm.getLimitOfRoom4());
		reservationCalender.add(calenderOfRoom4);
		
		
		manageService.updateReservationLimit(reservationCalender);
		
		return"manage/reservable_room_manage";
		
	}
}
