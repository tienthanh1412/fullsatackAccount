package com.vti.entites;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//để dùng được hibernate ta phải có 1 object mapping như class Account này
@Entity // de no hieu la 1 object lien ket voi co so du lieu
//@Data(công cụ này là tổng hợp của @Getter,@Setter,@ToString,@EqualsAndHashCode,@RequireArgsConstructor)cho ngắn gọn
@Data
@NoArgsConstructor //hàm khởi tọa rỗng
@Table(name = "account")
public class Account {

	@Column(name = "AccountID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "Email", length = 100, nullable = false, unique = false)
	private String email;

	@Column(name = "Username")
	private String userName;

	@Column(name = "Fullname")
	private String fullName;

	@ManyToOne
	@JoinColumn(name = "DepartmentID", referencedColumnName = "DepartmentID")
	private Department departments;

	@ManyToOne
	@JoinColumn(name = "PositionID", referencedColumnName = "PositionID")
	private Position position;


	@Column(name = "Password")
	private String password;


	@Column(name = "CreateDate")
	@Temporal(TemporalType.TIMESTAMP) // cai dat dinh dang ngay:ngaythangnam giophutgiay(co nhiều loại để chọn)
	private Date createDate;// Date import dung java.until
	
	
	
	@Column(name ="role")
	@Enumerated(EnumType.STRING)
	private Role role;
//khi them thuoc tinh ma data khong co se bao loi	@transient (neu muon them thuoc tinh ma data khong co thi su dung se khong bi loi)



	public Account(String name, String fullName, String email, Department departmentId, Position positionId,
			String password,Role role) {
		this.userName = name;
		this.fullName = fullName;
		this.email = email;
		this.departments = departmentId;
		this.position = positionId;
		this.password = password;
		this.role = role;

	}





	
	public String stringFormat() {
		return String.format(" %5d | %20s | %20s | %20s | %20s ", id, userName, fullName, email, createDate);
	}
}
