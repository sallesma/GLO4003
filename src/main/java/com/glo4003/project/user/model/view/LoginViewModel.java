package com.glo4003.project.user.model.view;

import com.glo4003.project.global.HasWarning;

public class LoginViewModel extends HasWarning {
	
	public LoginViewModel() {
		username = "";
		password = "";
	}
	
	private String username;
	
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
