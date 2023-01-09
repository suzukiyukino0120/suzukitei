package com.example.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.ReservationCalender;
import com.example.domain.Plan;
import com.example.form.SearchPlanForm;
import com.example.service.PlanService;
import com.example.validator.SearchPlanValidator;


@Controller
@RequestMapping("")
public class PlanController {
	
	@ModelAttribute
	public SearchPlanForm setUpSearchForm() {
		return new SearchPlanForm();
	}
	
	@Autowired 
	private HttpSession session;
	
	@Autowired
	private PlanService planService;
	
	@Autowired
	public SearchPlanValidator searchPlanValidator;
	
	@InitBinder("searchPlanForm")
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(searchPlanValidator);
    }
	
	@RequestMapping("/top")
	public String top() {
		
		ArrayList<ReservationCalender> list = new ArrayList<>();
		Calendar cal= Calendar.getInstance();
        cal.add(Calendar.MONTH, 3);
//        cal.set(Calendar.DATE, 1);
        
        int daysCount=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        Date date = new Date();
        
        for(int i = 1; i <= daysCount; i++) {
        	cal.set(Calendar.DATE, i);
        	date = cal.getTime();
        	
        	ReservationCalender reservationCalender1 = new ReservationCalender();
        	
        	reservationCalender1.setDate(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        	reservationCalender1.setReservedRoom(0);
        	reservationCalender1.setReservationLimit(5);
        	
        	ReservationCalender reservationCalender2 = reservationCalender1.clone(reservationCalender1);        	
        	ReservationCalender reservationCalender3 = reservationCalender1.clone(reservationCalender1);        	
        	ReservationCalender reservationCalender4 = reservationCalender1.clone(reservationCalender1); 
        	
        	reservationCalender1.setRoomId(1);
        	reservationCalender2.setRoomId(2);
        	reservationCalender3.setRoomId(3);
        	reservationCalender4.setRoomId(4);
        	
        	list.add(reservationCalender1);
        	list.add(reservationCalender2);
        	list.add(reservationCalender3);
        	list.add(reservationCalender4);
        	
        }
		System.out.println(list.size());
		System.out.println(list);
/*		
		List<ReservationCalender> list2 = new ArrayList<>();
		ReservationCalender reservationCalender1 = new ReservationCalender();
		ReservationCalender reservationCalender2 = new ReservationCalender();
		ReservationCalender reservationCalender3 = new ReservationCalender();
		ReservationCalender reservationCalender4 = new ReservationCalender();
		ReservationCalender reservationCalender5 = new ReservationCalender();
    	reservationCalender1.setDate(LocalDate.of(2015, 12, 1));
    	reservationCalender2.setDate(LocalDate.of(2015, 12, 10));
    	reservationCalender3.setDate(LocalDate.of(2015, 12, 11));
    	reservationCalender4.setDate(LocalDate.of(2015, 12, 19));
    	reservationCalender5.setDate(LocalDate.of(2015, 12, 25));
		
    	list2.add(reservationCalender1);
    	list2.add(reservationCalender2);
    	list2.add(reservationCalender3);
    	list2.add(reservationCalender4);
    	list2.add(reservationCalender5);
    	
    	List<ReservationCalender> list3 =	list2.stream().sorted(Comparator.comparing(ReservationCalender::getDate, Comparator.reverseOrder())).collect(Collectors.toList()); 
    	
    	System.out.println(list3);
*/		
		return "top";
	}
	
	@RequestMapping("/toPlanList")
	public String toPlanList() {
		session.removeAttribute("planList");
		return "plan_list";
	}
	
	
	@RequestMapping("/searchPlan")
	public String search(@Validated SearchPlanForm form, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return "plan_list";
		}
		
		
	
			
			
			
			//髯ｷ鮃ｹ莠ゑｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｮ髫ｶ�ｿｽ隲幢ｿｽ�ｽｽ�ｽｴ�ｿｽ�ｽｽ�ｽ｢鬩搾ｽｨ陷亥沺�ｽ｣�ｽ｡驛｢�ｽｧ陷ｻ闌ｨ�ｽｽ�ｽｶ陋ｹ�ｽｻ隨假ｿｽ
		session.removeAttribute("planList");
		session.removeAttribute("totalPriceList");
		session.removeAttribute("stayDays");
		session.removeAttribute("numOfGuest");
		session.removeAttribute("checkinDate");

		List<Plan> planList = new ArrayList();
		
		if(form.getDate() != null){
			planList= 
					planService.searchPlanWithDate(form.getSmoking(), form.getBathroom(), form.getBreakfast(), form.getDinner(), form.getDate(), form.getStayDays(), form.getNumOfGuest());
		
		}else {
			planList=
					planService.searchPlanWithoutDate(form.getSmoking(), form.getBathroom(), form.getBreakfast(), form.getDinner(), form.getPlanId(), form.getNumOfGuest());
		}
		
			List<Integer> totalPriceList = planService.calcTotalPrice(planList, form.getNumOfGuest(), form.getStayDays());

			session.setAttribute("planList", planList);
			session.setAttribute("totalPriceList", totalPriceList);
			session.setAttribute("stayDays", form.getStayDays());
			session.setAttribute("numOfGuest", form.getNumOfGuest());
			session.setAttribute("checkinDate", form.getDate());
			
			System.out.println(planList);
		
		return "plan_list";
		
	}

}
