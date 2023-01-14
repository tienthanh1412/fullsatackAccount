package com.vti.exception;

import com.vti.entites.ObjectErrorResponse;
//object này mang thông tin trả về client
//tự tao ra để chứa message và mã error mà mình quy định
public class CreateAccountException extends Exception {
	int errorCode;
//
	public CreateAccountException(String message, int errorCode) {
		super(message);//ném message vào và kế thừa Exception
		this.errorCode = errorCode;//mã lỗi mà mình quy định.
	}
	
	public ObjectErrorResponse  getObjectError() {
		return new ObjectErrorResponse(super.getMessage(),errorCode);

	}
}
