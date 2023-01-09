package com.example.controller;

import java.time.LocalDate;
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

import com.example.domain.Reservation;
import com.example.domain.Plan;
import com.example.form.ReservationForm;
import com.example.service.ReservationCalenderService;
import com.example.service.ReservationService;
import com.example.validator.ReservationValidator;


@Controller
@RequestMapping("/reservation")
public class ReservationController {

	@Autowired 
	private HttpSession session;
	
	@Autowired
	public ReservationValidator reservationValidator;
	
	@InitBinder("reservationForm")
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(reservationValidator);
    }
	
	@ModelAttribute
	public ReservationForm setUpReservationForm() { 
		
		//入力確認画面から入力画面に戻った時にフォームに表示されるようにする
		ReservationForm form = new ReservationForm();
		if(session.getAttribute("reservation") != null){
			Reservation reservation = (Reservation) session.getAttribute("reservation");
			BeanUtils.copyProperties(reservation, form);
		}
		
		return form;
	}
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private ReservationCalenderService reservationCalenderService;
	
	@RequestMapping("/loadReservationPage")
	public String loadReservationPage() {
		return "reservation";
	}
	
	@RequestMapping("/toReservation")
	public String toReservation(Integer planListNum,
							@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkinDate, Model model) {
		
		if(planListNum != null) {
			List<Plan> planList = (List<Plan>) session.getAttribute("planList");
			session.setAttribute("plan", planList.get(planListNum));
		}
		
		//検索条件で指定した宿泊人数、宿泊日数、チェックイン日を予約フォームに入力済みにする
		ReservationForm form = new ReservationForm();
		form.setNumOfGuest((Integer) session.getAttribute("numOfGuest"));
		form.setStayDays((Integer) session.getAttribute("stayDays"));
		if(checkinDate != null) {
			form.setCheckinDate(checkinDate);
		}else {
			form.setCheckinDate((LocalDate) session.getAttribute("checkinDate"));
		}
		model.addAttribute("reservationForm", form);
		
		return "reservation";
	}
	
	@RequestMapping("/confirmReservationForm")
	public String reservation(@Validated ReservationForm form, BindingResult result,  Model model) {
		if(result.hasErrors()) {
			return "reservation";
		}
		
		
		
		
		
		
		Reservation reservation = new Reservation();
		BeanUtils.copyProperties(form, reservation);
		
		//合計金額計算
		Plan chargeInfo = reservationService.checkCharge(form.getPlanId());
		reservation.setTotalPrice(reservation.calcTotalPrice(form.getNumOfGuest(), form.getStayDays(), chargeInfo.getAdditionalCharge(), chargeInfo.getBasicCharge()));
		
		session.setAttribute("reservation", reservation);
		
		return "reservation_confirm";
	}
	
	
	@RequestMapping("/complete")
	public String completeReservation(ReservationForm form, Model model) {
		
		//空室検索（予約フォームで日付変えた人、空室検索してない人、フォーム入力中に予約枠埋まる可能性を考慮）
		Plan plan = (Plan) session.getAttribute("plan");
		Integer roomId = plan.getRoomId();
		String fullReservationMsg
		= reservationService.checkFullReservation(form.getCheckinDate(), form.getCheckinDate().plusDays(form.getStayDays()-1), roomId);
		
		//満室の時エラ―ページ表示
		if(!"".equals(fullReservationMsg)) {
			model.addAttribute("fullReservationMsg", fullReservationMsg);
			return "full_reservation";
		}

		//予約テーブル更新
		Reservation reservation = (Reservation) session.getAttribute("reservation");
		reservationService.insert(reservation);
		
		//予約カレンダー更新
		reservationCalenderService.updateReservationCalender(form.getCheckinDate(), form.getStayDays()-1, roomId);
		
		return "redirect:/reservation/toComplete";
	}
	
	@RequestMapping("/toComplete")
	public String toComplete() {
		return "reservation_complete";
	}
	
}
