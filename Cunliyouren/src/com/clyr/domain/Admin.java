package com.clyr.domain;

import java.io.Serializable;

public class Admin implements Serializable {
	private static final long serialVersionUID = 715301753879209150L;
	//
	private String adminName;
	//
	private String password;

	public Admin() {
		super();
		this.adminName = "";
		this.password = "";
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
