package com.user;

import java.util.ArrayList;
import java.util.Scanner;

import com.user.controller.UserController;
import com.user.repository.UserRepository;
import com.user.service.UserService;
import com.user.response.WrapperResponse;
import com.user.dto.ResponseDTO;

public class Main {
	public static void main(String[] args) {
		boolean running = true;
		Scanner sc = new Scanner(System.in);
		UserRepository repo = new UserRepository();
		UserService service = new UserService(repo);
		UserController cont = new UserController(service, sc);
		while(running) {
			System.out.println("1.Add user");
			System.out.println("2.Delete user");
			System.out.println("3.Find user by id");
			System.out.println("4.Get all user");
			System.out.println("5.Exit");
			int select = sc.nextInt();
			switch(select) {
				case 1:
					WrapperResponse<?> add = cont.registUser();
					cont.printResponse(add);
					break;

				case 2:
					WrapperResponse<?> rem = cont.unregistUser();
					cont.printResponse(rem);
					break;

				case 3:
					WrapperResponse<?> src = cont.searchUser();
					cont.printResponse(src);
					break;

				case 4:
					WrapperResponse<ArrayList<ResponseDTO>> get = cont.getAllUser();
					cont.printResponse(get);
					break;
				case 5:
					running = false;
					break;

				default:
					System.out.println("Invalid");

			}
		}
	}
}
