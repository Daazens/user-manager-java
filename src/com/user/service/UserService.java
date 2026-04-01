package com.user.service;

import java.util.ArrayList;

import com.user.repository.UserRepository;
import com.user.dto.RequestDTO;
import com.user.dto.ResponseDTO;
import com.user.entity.User;

public class UserService {
	UserRepository repo;

	public UserService(UserRepository repo) {
		this.repo = repo;
	}

	public ResponseDTO createUser(RequestDTO data) {
		if (isValid(data)) {
			User user = new User(data.getId(), data.getName(), data.getEmail(), data.getPass());
			ResponseDTO userdto = mapToResponse(user);
			repo.save(user);
			return userdto;
		}
		else {
			return null;
		}
	}

	ResponseDTO mapToResponse(User u) {
		return new ResponseDTO(u.getName(), u.getEmail());
	}

	boolean isValid(RequestDTO user) {
		return user.getEmail().contains("@") && user.getPass().length() >= 5 &&
			!user.getName().isBlank();
	}

	public ResponseDTO deleteUser(int id) {
		User u = repo.findById(id);
		if (u != null) {
			if (u.getId() == id) {
				repo.delete(u);
				ResponseDTO dto = mapToResponse(u);
				return dto;
			}
			else {
				return null;
			}
		}
		return null;
	}

	public ResponseDTO searchUser(int id) {
		User u = repo.findById(id);
		if (u != null) {
			ResponseDTO dto = mapToResponse(u);
			return dto;
		}
		else {
			return null;
		}
	}

	public ArrayList<ResponseDTO> getUser() {
		ArrayList<User> users = repo.findAll();
		ArrayList<ResponseDTO> list = new ArrayList<>();
		for (User u : users) {
			ResponseDTO dto = mapToResponse(u);
			list.add(dto);
		}
		return list;
	}
}
