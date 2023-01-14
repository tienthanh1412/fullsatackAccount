package com.vti.entites.Specification;

import org.springframework.data.jpa.domain.Specification;

import com.vti.entites.Account;
import com.vti.entites.Fillter.AccountFillter;

//Specification là câu điều kiện(WHERE) của Spring DATA(thư viện spring boot JPA DATA) nối vào câu lệnh cơ bản của JPA để tránh viết nhiều câu lệnh đơn giản thì ta viết 1 câu lệnh thôi xong truyền fillter từ cái fillter sẽ sinh ra câu điều kiện và nối vào câu điều kiện chính	
//class này sinh ra để nối các câu điều kiện nhỏ lại với nhau(nó là class trung gian)
public class AccountSpecification {
	// phương thức Static không cần khởi tạo và có thể gọi ở bất kì đâu public
	// fillTer là object chứa thông tin gửi lên
	public static Specification<Account> buidWhere(AccountFillter fillTer) {// buidWhere là tổng hợp các biến nhỏ của
																			// thằng class CustomSpecification với nhau
		if (fillTer == null) {// nếu trống thì trả luôn về giá trị null
			return null;
		} // nếu có giá trị sẽ sinh ra các câu điều kiện ở dưới
//		tạo mới các câu điều kiện ở class CustomSpecification
		Specification<Account> where1 = new CustomSpecification(AccountKey.USERNAME, fillTer.getUsername());																								
		// truyền cái Key:AccountKey.USERNAME này vào thì sẽ sinh điều kiện theo key và trả về sẽ gán vào  where1

		Specification<Account> where2 = new CustomSpecification(AccountKey.EMAIL, fillTer.getEmail());
		Specification<Account> where3 = new CustomSpecification(AccountKey.FULLNAME, fillTer.getFullName());
		Specification<Account> where4 = new CustomSpecification(AccountKey.MIN_ID,
				fillTer.getMinId() > 0 ? fillTer.getMinId() + "" : null);// toán tử 3 ngôi
		Specification<Account> where5 = new CustomSpecification(AccountKey.MAX_ID,
				fillTer.getMaxId() > 0 ? fillTer.getMaxId() + "" : null);
		Specification<Account> where6 = new CustomSpecification(AccountKey.DATE, fillTer.getDate());//tìm theo ngày
		Specification<Account> where7 = new CustomSpecification(AccountKey.MIN_DATE,fillTer.getMinDate());
		Specification<Account> where8 = new CustomSpecification(AccountKey.MAX_DATE,fillTer.getMaxDate());
		Specification<Account> where9 = new CustomSpecification(AccountKey.DEPARTMENT_NAME,fillTer.getDepartmentName());
		Specification<Account> where10 = new CustomSpecification(AccountKey.POSITION_NAME,fillTer.getPositionName());
		
		

//nối các câu điều kiện lại ta được câu điều kiện tổng: where userName = "value" or email= "%value%" đây là câu query của dòng dưới
		return Specification.where(where1).or(where2).or(where3).and(where4).and(where5).and(where6).and(where7).and(where8).and(where9).and(where10); // Tập hợp lại  câu điều
																							// kiện, muốn tìm theo điểu
																							// kiện gì .or là
		// tìm theo 1 trong 2(có thê toán tử and để tìm cả 2). return câu điểu kiện này
		// vào thằng Sevice
	}

}
