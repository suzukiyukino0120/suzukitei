package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Administrator;
import com.example.form.AdminLoginForm;
import com.example.form.AdministratorForm;
import com.example.service.AdministratorService;
import com.example.validator.LoginAdminValidator;
import com.example.validator.RegisterAdminValidator;

@Controller
@RequestMapping("/administrator")
public class AdministratorContoroller {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private AdministratorService administratorService;

	@ModelAttribute
	public AdministratorForm setUpAdministratorForm() {
		return new AdministratorForm();
	}
	
	@ModelAttribute
	public AdminLoginForm setUpAdminLoginForm() {
		return new AdminLoginForm();
	}
	
	@Autowired
	public RegisterAdminValidator registerAdminValidator;
	
	@InitBinder("administratorForm")
    public void registerValidatorBinder(WebDataBinder binder) {
        binder.addValidators(registerAdminValidator);
    }
	
	@Autowired
	public LoginAdminValidator loginAdminValidator;
	
	@InitBinder("adminLoginForm")
	public void loginValidatorBinder(WebDataBinder binder) {
		binder.addValidators(loginAdminValidator);
	}

	@RequestMapping("/toLogin")
	public String toLogin() {
		return "administrator/login";
	}
	
	@RequestMapping("/toRegister")
	public String toRegister() {
		return "administrator/administrator_register";
	}
	
	@RequestMapping("/register")
	public String register(@Validated AdministratorForm administratorForm, BindingResult result, Model model) {
		if(result.hasErrors()) {
				return toRegister();
		}
		
		Administrator administrator = new Administrator();
		BeanUtils.copyProperties(administratorForm, administrator);
		
		administratorService.insert(administrator);
		
		return "administrator/login";
	}
	
	@RequestMapping("/login")
	public String login(@Validated AdminLoginForm form, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "administrator/login";
		}

		return "redirect:/manage/toReservationList";
		
	}
	
	@RequestMapping("/logout")
	public String logout() {
//		session.invalidate();		
		return "administrator/login";
	}
	
	
}
