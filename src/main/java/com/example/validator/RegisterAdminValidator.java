package com.example.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.form.AdministratorForm;
import com.example.service.AdministratorService;
import com.example.service.EmployeeService;

@Component
public class RegisterAdminValidator implements Validator{
	
	@Autowired
	private EmployeeService employeeService;	
	
	@Autowired
	private AdministratorService administratorService;
	
	@Override
    public boolean supports(Class<?> clazz) {

		return AdministratorForm.class.isAssignableFrom(clazz);
    }
	
	@Override
	    public void validate(Object form, Errors errors) {
		AdministratorForm validationForm = (AdministratorForm)form;
		
	        if(!validationForm.getPassword().equals(validationForm.getPasswordConfirm())){
				errors.rejectValue("password", "", "パスワードが一致していません");
			}
			if(employeeService.load(validationForm.getEmployeeId()) == null) {
				errors.rejectValue("employeeId", "", "存在しない社員番号です");
			}else if(administratorService.load(validationForm.getEmployeeId()) != null) {
				errors.rejectValue("employeeId", "", "この社員番号で既に登録されています");
			}
	    }




}
