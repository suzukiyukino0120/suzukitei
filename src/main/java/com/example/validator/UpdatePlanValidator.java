package com.example.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.validation.Validator;

import com.example.form.UpdatePlanForm;

@Component
public class UpdatePlanValidator implements Validator{
	
	@Override
    public boolean supports(Class<?> clazz) {

		return UpdatePlanForm.class.isAssignableFrom(clazz);
    }
	
	@Override
	    public void validate(Object form, Errors errors) {
	    	UpdatePlanForm validationForm = (UpdatePlanForm)form;
	    	String nowImage = validationForm.getNowImage();
	        MultipartFile image = validationForm.getImage();
	        
	        if("change".equals(nowImage)) {
				if(image.isEmpty()) {
					errors.rejectValue("image","", "イメージを変更する場合はイメージを添付してください。");
				}
			}else {
				if(!image.isEmpty()) {
					errors.rejectValue("image","", "イメージを変更しない場合はイメージを添付しないでください");
				}
			}
	    }




}
