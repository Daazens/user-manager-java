package com.user.dto;

public class ResponseDTO {
	private String name;
	private String email;

	public ResponseDTO(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String toString() {
		return "\nname   : " + name + "\nemail  : " + email;
	}
}

