package com.example.service;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Plan;
import com.example.repository.PlanMapper;

@Service
@Transactional
public class PlanService {

	@Autowired
	private PlanMapper planMapper;
	
	public List<Plan> searchPlanWithDate(Integer smoking, Integer bathroom, Integer breakfast, Integer dinner, LocalDate date, Integer stayDays, Integer numOfGuest){
		List<Plan> planList = planMapper.findPlanWithDate(smoking, bathroom, breakfast, dinner, date, stayDays, numOfGuest);
		
		//空室あるプランを除く
		Iterator<Plan> it = planList.iterator();
		while(it.hasNext()){
			Plan plan = it.next();
			if(plan.getRoom().getReservationCalender().size()<stayDays+1) {
				it.remove();
			}
		}
		
		return planList;
	}
	
	public List<Plan> searchPlanWithoutDate(Integer smoking, Integer bathroom, Integer breakfast, Integer dinner, Integer planId, Integer numOfGuest){
		return planMapper.findPlanWithoutDate(smoking, bathroom, breakfast, dinner, planId, numOfGuest);
	}
	
	//検索画面で表示させるプランごとの宿泊料金を計算するメソッド
	public List<Integer> calcTotalPrice(List<Plan> planList, Integer numOfGuest, Integer stayDays){
		List<Integer> totalPriceList = new LinkedList<>();
		
		for(Plan plan : planList) {
			Integer totalprice = (plan.getBasicCharge() + plan.getAdditionalCharge() * (numOfGuest - 1)) * stayDays;
			totalPriceList.add(totalprice);
		}
		
		return totalPriceList;
	}
	


}
