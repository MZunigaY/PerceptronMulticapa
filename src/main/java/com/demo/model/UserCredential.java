package com.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCredential {

	private String username;
	private String password;

	public UserCredential() {
	}

	public UserCredential(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserCredential [username=" + username + ", password=" + password + "]";
	}

}
