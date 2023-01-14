package com.vti.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.vti.entites.Account;
import com.vti.entites.Department;
import com.vti.entites.Position;
import com.vti.entites.PositionName;

public class MainTest {
	public static void main(String[] args) {
//		// Khởi tạo AccountController ra để lấy các phương thức của nó
//		AccountController Controller = new AccountController();
//
//		DepartmentController departmentController = new DepartmentController();
//		PositionController pos = new PositionController();
//		List<Position> list = pos.getAllPosition();
//		for (Position position : list) {
//			System.out.println(position.toString());
//		}

		// Department department = departmentController.createDepartment(9, "Phong
		// Event");
//		 List<Department>list = departmentController.getAllDepartment();
//		 for (Department department : list) {
//		 System.out.println(department.toString());
//		 }
// ============================================================================================
//		try {
////	cụm Controller.getAllAccount(); sẽ trả về 1 list Acc và kết quả đó sẽ được gán vào biến list
//			List<Account> list = Controller.getAllAccount(); 
////	Sau đó duyệt mảng
//			for (Account account : list) {
//				
//
//				System.out.println(account.toString());
//			}
//		} catch (Exception e) {
//			System.err.println(e.getMessage());
//		}

//============================================================================================
//		try {
//			Account account = Controller.getAccById(); // vi ham .getAccById(0); lay ra doi tuong Account nen goi ra
//															// doi tuong Account
//
//		} catch (Exception e) {
//			System.err.println(e.getMessage());
//		}

		// ============================================================================================
//		Account account = null;
//		try {
//			account = Controller.createAcc("Khá Bảnh", "Ngô Bá Khá", "Muaquat@gmail.com",
//					new Department(1, "Phong Ky Thuat 1"), new Position(3, PositionName.LEADER), "123456");
//		} finally {
//			if (account != null) {
//				System.out.println("Tạo mới thành công");
//			}
//		}

//============================================================================================
//		try {
//			Account account = Controller.updateAcc(16, "Are you ok", "OK Ok OK", "Ok@gmail.com");
//			if(account != null) {
//				System.out.println("Update Thành Công");
//			}
//		} catch (Exception e) {
//			System.err.println(e.getMessage());
//		}

//============================================================================================
//		try {
//			Account account = Controller.deleteAccById(12);
//			if(account != null) {
//				System.out.println("Xóa Thành Công");
//			}
//		} catch (Exception e) {
//			System.err.println(e.getMessage());
//		}

// ============================================================================================
//		try {
//			List<Account> list = Controller.getAllAccountFillter(1, 13, null);
//			for (Account account : list) {
//				System.out.println(account.toString());
//			}
//		} catch (Exception e) {
//			System.err.println(e.getMessage());
//		}
//=============================================================================================
//		List<Account> list = Controller.getAllAccountByDepartment(3);
//		for (Account account : list) {
//			System.out.println(account.stringFormat());
//		}

//		List<Account> list = Controller.getAllAccountFillter(2, 5, null);
//		for (Account account : list) {
//			System.out.println(account.stringFormat());
//		}
//
	}
}
