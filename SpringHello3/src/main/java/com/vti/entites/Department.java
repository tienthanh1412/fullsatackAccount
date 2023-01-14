package com.vti.entites;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "department")
public class Department {
	@Column(name = "DepartmentID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "DepartmentName")
	private String name;

	@OneToMany(mappedBy = "departments") // @OneToMany 1 đối tượng department có thể chứa nhiều account
											// chú ý giá trị của mappedBy là tên của thuộc tính ánh xạ trong (mappedBy
											// là
											// trỏ đến private Department department bên class Account);.
//	@JoinColumn(name = "DepartmentID" , referencedColumnName = "DepartmentID")//cách 1:join cột thẳng đến cột luôn
//	@JoinColumn(foreignKey = "Tên của khóa ngoại") cách 2 : gọi đến tên khóa ngoại
	private List<Account> listAccounts; // 1 phòng ban chứa nhiều account



	public Department(String name) {

		this.name = name;
		
	}

//	public String StringFomat() {
//		return String.format(""%5d | % 20s", id, name);
//	}

}
