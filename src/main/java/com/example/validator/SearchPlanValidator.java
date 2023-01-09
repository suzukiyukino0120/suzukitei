package com.example.validator;

import java.time.LocalDate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.form.SearchPlanForm;

@Component
public class SearchPlanValidator implements Validator{
	
	
	@Override
    public boolean supports(Class<?> clazz) {

		return SearchPlanForm.class.isAssignableFrom(clazz);
    }
	
	@Override
	    public void validate(Object form, Errors errors) {
		SearchPlanForm validationForm = (SearchPlanForm)form;

		LocalDate today = LocalDate.now();
		if(validationForm.getDate() != null) {
			if(today.compareTo(validationForm.getDate()) >= 0) {
				errors.rejectValue("date", "", "明日以降の日付を入力してください");
			}
		}
	}
}
