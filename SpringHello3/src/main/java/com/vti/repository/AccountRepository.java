package com.vti.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.vti.entites.Account;
import com.vti.entites.Department;
import com.vti.entites.Position;
import com.vti.entites.Fillter.AccountFillter;
import com.vti.entites.Form.AccountCreateForm;
import com.vti.untils.HibernateUntil;
//@Repository
public class AccountRepository implements IAccountRepository {
//hibernate là 1 thành phần thư viện nâng cấp của JDBC với mục đích lấy dữ liệu từ database lên,nó đặc biệt hơn JDBC là không phải thực hiện câu lệnh với SQL mà nó viêt câu lệnh =hibernate Query trên oject và các oject của nó sẽ được cấu hình mapping với data(VD: @column,@ID,...)	
	// hibernate dua tren kien truc ORM thi no se lay class trong object lien ket
	// voi table trong data,khi truy van SQL thi ta khong phai truy van trong data
	// nua ma truy van thong qua class,no tra ve 1 object va maping den data roi
	// data lay du lieu tuong ung dien vao object do

// lay tat ca acccount==================================================================================================

	public List<Account> getAllAccount() {
		Session session = null;//các câu lệnh của hibernate phải thực hiện trên Session
		try {
//ta có thành phần Factory() tạo ra 1 cái session(openSession) (như 1 phiên làm việc)
			session = HibernateUntil.getFactory().openSession(); // tao moi session de goi ra HibernateUntil,truoc
																	// khi
																	// goi gi do ta phai open cai session
//sau khi có session thì ta thực hiện câu truy vấn các câu lệnh hibernate(câu lệnh này có thể lên gg xem thêm)
			String hqlQuery = "FROM Account"; // cau lenh query trong Hibernate ==> HQl khong phai query voi data(query
												// voi class Account trong entites phai viet dung ten class)
//thực hiện cú pháp để lấy câu select "FROM Account" ta có object Query sẽ trả về đối tượng											
			Query<Account> query = session.createQuery(hqlQuery); // goi session va su dung bien Query de tra ve 1 list
																	// Acc,create se
																	// truyen vao String(la cau lenh hqlQuery)
//sau đó . phương thức list() để trả về 1 danh sách Acc
			List<Account> listResult = query.list(); // cau hqlQuery tren se tra ve 1 list,khi su dung hibernate thi no
														// tra
														// ve 1 object luon,khi co object t chi can goi lenh foreach de
														// duyet mang object
// khi lấy được 1 list Acc thì return nó ra.
			return listResult;
		} finally {
			if (session != null) {
//cuối cùng khi thực hiện xong thì đóng cái phiên làm việc lại(session)
				session.close();
			}
		}

	}

// GET BY ID=============================================================================================================
	public Account getAccById(int id) {
		Session session = null;
		try {
			session = HibernateUntil.getFactory().openSession();
			String hqlQuery = "FROM Account A WHERE A.id = " + id; // thuoc tinh id ben trong la thuoc tinh id cua class
																	// chu khong phai trong data
			Query<Account> query = session.createQuery(hqlQuery);
			Account account = query.uniqueResult(); // lay ve 1 ket qua
			// List<Account> listResult = query.list(); lay ve nhieu ket qua va su dung vong lap foreach

			return account;
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

//CREATE==========================================================================================================

	public Account createAcc(Account account) {
		Session session = null;

//		try {
//			session = HibernateUntil.getFactory().openSession();
//			Account account = new Account(name, fullName, email, departmentId, positionId, password);// (them 1 ham khoi
//																										// tao = voi so
//																										// tham so
//																										// moi).ngoài
//			// lề(trang thai dau tien khi ta khoi tao thi chi co
//			// java hieu còn hibernate và data không hiểu goi la
//			// khong lien ket)
//			session.save(account);// (de luu du lieu tren vao data,hoac them moi data).ngoài lề(trang thai thu 2
//									// khi save vào hoặc
//									// get ra từ hibernate thì cái account này nó đã liên kết với data rồi(trạng
//									// thái persistence)
//			return account;// tra ve acc
//		} finally {
//			if (session != null) {
//				session.close(); // (trang thái transient la tam thoi ngat lien ket )
//			}
//		}
		
		try {
			session = HibernateUntil.getFactory().openSession();
			session.beginTransaction();
			session.save(account);
			session.getTransaction().commit();
			return account;
		} finally {
			if(session != null) {
				session.close();
			}
		}

	}

// UPDATE==========================================================================================================
	
//	public Account updateAcc(int id, String name, String fullName, String email) {
//		Session session = HibernateUntil.getFactory().openSession();
//
//		session.beginTransaction(); // su dung khi dele hoac update
//
//		String hqlQuery = "FROM Account A WHERE A.id = " + id; // dau tien lay du lieu trong data ra
//
//		Query<Account> query = session.createQuery(hqlQuery);// khi lay ra thì doi tuong nay da lien ket voi data
//
//		Account account = query.uniqueResult();
//
//		if (account == null) {
//			System.err.println("Không tìm thấy Account phù hợp");
//			return null;// vi neu khong tim thay sẽ dừng hẳn,không cần thêm else
//		}
//// sau do thay doi thong tin cua object cua doi tuong
//		if (name != null) {
//			account.setUserName(name);
//		}
//		if (fullName != null) {
//			account.setFullName(fullName);
//		}
//		if (email != null) {
//			account.setEmail(email);
//		}
//
//		session.save(account); // roi save lai thi no hieu la doi tuong da lien ket trong data.
//		session.getTransaction().commit(); // khi thay doi du lieu thi commit lai thi no se tu dong thay doi trong data
//		session.close();
//		return account;
//
//	}
	
	public Account updateAcc(Account account) {
		Session session = null;
		try {
			session = HibernateUntil.getFactory().openSession();
			session.beginTransaction();
			session.update(account);
			session.getTransaction().commit();
			return account;
		} finally {
			if(session!= null) {
				session.close();
			}
			
		}
		}

	// DELETE==========================================================================================================
	/**
	 * id:chọn id muốn xóa trong Account
	 * 
	 */
	public Account deleteAccById(int id) {
		Session session = null;
		try {
			session = HibernateUntil.getFactory().openSession();// tao moi session de goi ra HibernateUntil

			Account account = session.get(Account.class, id);// tim ra id cua Acc muon tim

			session.beginTransaction(); // su dung khi dele hoac update de co the rollback lai
			session.delete(account); // thuc hien lenh xoa Acc
			session.getTransaction().commit(); // khi thay doi du lieu thi commit lai thi no se tu dong thay doi trong
												// data
			return account;
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

//GET BY FILLTER=======================================================================================================================
	/**
	 * Lấy tất cả Account trong CSDL bằng cách phân trang ra
	 * 
	 * @param page    Số trang
	 * @param size    Số lượng Phần tử trong 1 trang
	 * @param fillter
	 * @return Trả về đối tượng List Account
	 */
	public List<Account> getAllAccountFillter(int page, int size, AccountFillter fillter) {
		Session session = null;
		try {
			session = HibernateUntil.getFactory().openSession();// để tạo truy vấn ta taọ ra thành phần session

			String hqlQuery = "FROM Account"; // Sau đó tạo Câu lệnh query trong Hibernate => HQL,Account la class trong
												// java
//logic =
			if (fillter != null) {
				if (fillter.getEmail() != null) {
					hqlQuery = "FROM Account A WHERE A.email LIKE '%" + fillter.getEmail() + "%'";
				}

				if (fillter.getUsername() != null) {
					hqlQuery = "FROM Account A WHERE A.username = '" + fillter.getUsername() + "'";
				}

				if (fillter.getEmail() != null && fillter.getUsername() != null) {
					hqlQuery = "FROM Account A WHERE A.username = '" + fillter.getUsername() + "'"
							+ " OR A.email LIKE '%" + fillter.getEmail() + "%'";
				}
			}

			int offset = (page - 1) * size;// vị trí bắt đầu
			// int limit = size;

			@SuppressWarnings("unchecked")//tắt cảnh báo lỗi(có thể thêm hoặc không thêm)
			Query<Account> query = session.createQuery(hqlQuery);
			query.setFirstResult(offset);
			query.setMaxResults(size); // số lượng phần tử tối đa lấy ra

			List<Account> listResult = query.list();

			return listResult;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
//======================================================================================================
	@Override
	public List<Account> getAllAccountByDepartment(int id) {
		Session session = null; // gọi đến Session
		try {
			session = HibernateUntil.getFactory().openSession(); // gán session để gọi ra HibernateUntil
			String hqlquery = "FROM Account A WHERE A.department = " + id; // Tạo ra câu lệnh hqlquery (A.department là
																			// thuộc tính trong class Account)
			@SuppressWarnings("unchecked") // loại bỏ cảnh báo,
			Query<Account> query = session.createQuery(hqlquery);// thực hiện câu query liên kết với database
			List<Account> list = query.list(); // trả về đối tượng list account trong object
			return list;
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}



}
