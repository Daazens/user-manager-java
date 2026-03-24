package com.user.entity;

public class User {
	private String name;
	private String password;
	private String email;
	private int id;

	public User(int id, String name, String email, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPass() {
		return password;
	}

	public int getId() {
		return id;
	}
}
