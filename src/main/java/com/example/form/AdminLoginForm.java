package com.example.form;

import javax.validation.constraints.NotNull;

public class AdminLoginForm {
	@NotNull(message="従業員番号を入力してください")
	private Integer employeeId;
	@NotNull(message="パスワードを入力して下さい")
	private String password;

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

	@Override
	public String toString() {
		return "AdminLoginForm [employeeId=" + employeeId + ", password=" + password + "]";
	}
	
	

}
