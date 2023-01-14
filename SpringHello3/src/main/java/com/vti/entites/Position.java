package com.vti.entites;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "Position")
// Serializable la 1 thu vien cua java co nhiem vu chuyen object thanh byte va nguoc lai
public class Position implements Serializable {

	private static final long serialVersionUID = 1L;
	@Column(name = "PositionID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "PositionName", nullable = false)
	@Enumerated(EnumType.STRING)
	private PositionName positionName;

//	@OneToMany(mappedBy = "position")
//	private List<Account> listAccounts;

//	public Position() {
//
//	}
//hàm chuyển đổi dữ liệu enum thành String
	public Position(String textName) { //truyền vào 1 cái tên và xét cho positionName
		for (PositionName item : PositionName.values()) { //chạy mảng enum của PositionName
			if(textName.equals(item.name())) {//ktra xem giá trị truyền vào có giống enum không
				this.positionName = item;//nếu trùng thì xét nó vào
			}
			
		}
	}

//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public PositionName getPositionName() {
//		return positionName;
//	}
//
//	public void setPositionName(PositionName positionName) {
//		this.positionName = positionName;
//	}
//	
//
////	public List<Account> getLisAccounts() {
////		return lisAccounts;
////	}
//
//	public void setLisAccounts(List<Account> lisAccounts) {
//		this.lisAccounts = lisAccounts;
//	}
//
//	@Override
//	public String toString() {
//		return "Position [id=" + id + ", positionName=" + positionName + "]";
//	}

	public String stringFormat() {
		return String.format(" %5d | %20s ", id, positionName);
	}

}
