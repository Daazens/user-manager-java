package com.user.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.user.dto.ResponseDTO;
import com.user.dto.RequestDTO;
import com.user.repository.UserRepository;
import com.user.response.WrapperResponse;
import com.user.service.UserService;

public class UserController {
	Scanner sc;
	UserService service;
	public int idQounter = 1;

	public UserController(UserService service, Scanner sc) {
		this.service = service;
		this.sc = sc;
	}

	public WrapperResponse<ResponseDTO> registUser() {
		sc.nextLine();
		System.out.println("email must be contain '@'");
		System.out.println("password length must be more than 5");
		System.out.print("Name    : ");
		String name = sc.nextLine();
		System.out.print("email   : ");
		String email = sc.nextLine();
		System.out.print("password: ");
		String pass = sc.nextLine();
		RequestDTO user = new RequestDTO(idQounter++, name, email, pass);
		ResponseDTO dto = service.createUser(user);
		if (dto != null) {
			return new WrapperResponse<ResponseDTO>("success", "created", dto);
		}
		else {
			idQounter--;
			return new WrapperResponse<ResponseDTO>("error", "not valid", null);
		}
	}

	public WrapperResponse<ResponseDTO> unregistUser() {
		System.out.print("User id > ");
		int id = sc.nextInt();
		ResponseDTO dto = service.deleteUser(id);
		if (dto != null) {
			return new WrapperResponse<>("success", "deleted", dto);
		}
		else {
			return new WrapperResponse<>("error", "not found", null);
		}
	}

 
	public WrapperResponse<ResponseDTO> searchUser() {
		System.out.print("User id >");
		int select = sc.nextInt();
		ResponseDTO dto = service.searchUser(select);
		if (dto != null) {
			return new WrapperResponse<>("success", "found", dto);
		}
		else {
			return new WrapperResponse<>("error", "not found", null);
		}
	}

	public WrapperResponse<ArrayList<ResponseDTO>> getAllUser() {
		ArrayList<ResponseDTO> list = service.getUser();
		if (list != null) {
			return new WrapperResponse<>("success", "found", list);
		}
		else {
			return new WrapperResponse<>("error", "empty", null);
		}
	}

	public void printResponse(WrapperResponse <?> data) {
		System.out.println("Status : " + data.status);
		System.out.println("Message: " + data.message);
		System.out.println("Data   : " + data.data);
	}
}
