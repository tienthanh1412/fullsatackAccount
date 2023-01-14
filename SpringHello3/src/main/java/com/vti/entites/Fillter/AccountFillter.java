package com.vti.entites.Fillter;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//class này được tạo ra để tiếp nhận dữ liệu rồi lọc kết quả ra
public class AccountFillter {
	// muốn lọc thuộc tính nào thì thêm vào thuộc tính đó
	private String email;
	private String username;
	private String fullName;
	private int minId;
	private int maxId;
	@DateTimeFormat(pattern = "dd-MM-yyyy") // cấu hình định dạng thời gian ở đây là ngày tháng năm để truyền date vào
											// tham số date(còn muốn tìm theo năm thì để mỗi yyyy hay tìm mỗi tháng thì
											// để MM)
	private Date date;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date minDate;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date maxDate;
	
	private String departmentName;
	private String positionName;

	// sử dụng lumbok xóa dưới đi
//	public AccountFillter() {
//	}
//
	public AccountFillter(String email, String username, String fullName) {
		super();
		this.email = email;
		this.username = username;
		this.fullName = fullName;
	}
//
//	public String getFullName() {
//		return fullName;
//	}
//
//	public void setFullName(String fullName) {
//		this.fullName = fullName;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}

}
