package com.example.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class AdministratorForm {
	
	@NotNull(message="従業員番号を入力してください")
	private Integer employeeId;
	
	@NotBlank(message="パスワードを入力してください")
	@Pattern(regexp="^[a-zA-Z0-9]{8,16}$", message="正しく入力してください")
	private String password;
	
	@NotBlank(message="確認用パスワードを入力してください")
	private String passwordConfirm;

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	@Override
	public String toString() {
		return "AdministratorForm [employeeId=" + employeeId + ", password=" + password + ", passwordConfirm="
				+ passwordConfirm + "]";
	}

	
	
	
}
