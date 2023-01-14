package com.vti.entites.Specification;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.aspectj.weaver.reflect.Java14GenericSignatureInformationProvider;
import org.springframework.data.jpa.domain.Specification;

import com.vti.entites.Account;
import com.vti.entites.PositionName;

// class này để sinh ra các điều kiện  nhỏ để cho class AccountSpecification tập hợp lại thành điều kiện lớn
public class CustomSpecification implements Specification<Account> {
	private String key;
	private Object value;

	public CustomSpecification(String key, Object value) {// truyền tham số vào đây key:tương ứng với truyền cái chữ gì
															// vào tương ứng với cái key đấy
		super();
		this.key = key;
		this.value = value;
	}

// đây là cái sẽ sinh ra câu lệnh where
	@Override
	public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		if (value == null) {// nếu không có giá trị gì thì trả luôn về null cho nó không phải check
			return null;
		}
		switch (key) {
		case AccountKey.USERNAME:
			return criteriaBuilder.equal(root.get("userName"), value); // root.get("userName") là get cái thuộc tính
																		// trong class Account (sẽ là equal(tìm kiếm =
																		// giống
																		// )lấy thuộc tính userName where câu lệnh
																		// value mình truyền vào
																		// VD :where userName="value"

		case AccountKey.EMAIL:
			return criteriaBuilder.like(root.get("email"), "%" + value + "%");// dùng toán tử like(tương tự) VD:where
																				// userName="%value%"

		case AccountKey.FULLNAME:
			return criteriaBuilder.equal(root.get("fullName"), value);

		case AccountKey.MIN_ID:
			return criteriaBuilder.greaterThanOrEqualTo(root.get("id"), value.toString());// where id >= "value" (lưu
																							// ý:id là thuộc tính của
																							// class Account)

		case AccountKey.MAX_ID:
			return criteriaBuilder.lessThanOrEqualTo(root.get("id"), value.toString());// where id <= "value"
		case AccountKey.DATE:
			return criteriaBuilder.equal(root.get("createDate").as(java.sql.Date.class), (Date) value);// tìm theo
																										// ngày(as(java.sql.Date.class),(Date)value
																										// ép kiểu SQL cho date để lấy dữ liệu
																										// trong Data còn:
																										// (Date)value date ở đây là của java util
		case AccountKey.MIN_DATE:
			return criteriaBuilder.greaterThanOrEqualTo(root.get("createDate").as(java.sql.Date.class), (Date) value);
			
		case AccountKey.MAX_DATE:
			return criteriaBuilder.lessThanOrEqualTo(root.get("createDate").as(java.sql.Date.class), (Date) value);
			
		case AccountKey.DEPARTMENT_NAME://get("departments") là thuộc tính của departments trong class Account,get("name") của class deparrtment
//	cách 1:		return criteriaBuilder.like(root.get("departments").get("name"), "%" + value.toString() +"%");
//	cách 2:
			Join join = root.join("departments",JoinType.INNER);//join từ thuộc tính departments của class Account
			return criteriaBuilder.like(join.get("name"), "%" + value.toString() +"%");//join đến name của class Department
			
		case AccountKey.POSITION_NAME:
			return criteriaBuilder.like(root.get("position").get("positionName"), "%" +  value +"%");
																										

		default:
			break;
		}
		return null;
	}

}
