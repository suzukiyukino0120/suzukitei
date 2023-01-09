package com.example.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.domain.Administrator;
import com.example.form.AdminLoginForm;
import com.example.service.AdministratorService;

@Component
public class LoginAdminValidator implements Validator{
	
	
	@Autowired
	private AdministratorService administratorService;
	
	@Override
    public boolean supports(Class<?> clazz) {

		return AdminLoginForm.class.isAssignableFrom(clazz);
    }
	
	@Override
	public void validate(Object form, Errors errors) {
		AdminLoginForm validationForm = (AdminLoginForm)form;
		
		Administrator administrator = administratorService.findByIdAndPass(validationForm.getEmployeeId(), validationForm.getPassword());

		if(administrator == null) {
			errors.rejectValue("employeeId", "", "社員番号かパスワードが間違っています");
		}
	}




}
