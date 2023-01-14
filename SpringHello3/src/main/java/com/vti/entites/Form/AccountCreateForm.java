package com.vti.entites.Form;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//Mục đích post dữ liệu lên không bị lộ thông tin ở đường dẫn,thay vì truyền dữ liệu vào parameter ta truyền dữ liệu trong phần body
//lưu ý có 1 số dữ liệu trong data là not null,nhỡ thêm đủ.
public class AccountCreateForm {
	@NotBlank(message = "the name mustn't be null value")//in ra thông báo khi để null hoặc rỗng
	@Length(max = 50,message = "the name's length is max 50 characters")//in ra thông báo khi nhiều hơn 50 kí tự
	private String userName;
	private String fullName;
	private String email;
	private int dpId;
	private int posId;

	private String password;

	private String role;


}
