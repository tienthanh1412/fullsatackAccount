package com.vti.entites;

//mục đích tạo ra là khi ném ra 1 cái lỗi thì nó sẽ trả cái lỗi đấy theo dạng object
//khi client bị lỗi sẽ trả thông tin về cái object này (object chứa thông tin lỗi);
public class ObjectErrorResponse {
	private String message;

	private int codeError;//mã lỗi

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCodeError() {
		return codeError;
	}

	public void setCodeError(int codeError) {
		this.codeError = codeError;
	}

	public ObjectErrorResponse() {

	}

	public ObjectErrorResponse(String message, int codeError) {
		super();
		this.message = message;
		this.codeError = codeError;
	}

}
