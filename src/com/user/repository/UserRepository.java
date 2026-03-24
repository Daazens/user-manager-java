package com.user.repository;

import com.user.entity.User;
import java.util.ArrayList;

public class UserRepository {
	ArrayList<User> users = new ArrayList<>();

	public User save(User user) {
		users.add(user);
		return user;
	}

	public User delete(User user) {
		users.remove(user);
		System.out.println("delete");
		return user;
	}

	public ArrayList<User> findAll() {
		return users;
	}

	public User findById(int id) {
		if (users.isEmpty()) {
			return null;
		}
		for (User u : users) {
			if (u.getId() == id) {
				return u;
			}
		}
		return null;
	}
 }
