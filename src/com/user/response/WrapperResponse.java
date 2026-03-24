package com.user.response;

public class WrapperResponse<T> {
	public String message;
	public String status;
	public T data;

	public WrapperResponse(String status, String message, T data) {
		this.message = message;
		this.status = status;
		this.data = data;
	}
}
