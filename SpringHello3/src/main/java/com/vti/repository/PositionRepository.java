package com.vti.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.vti.entites.Department;
import com.vti.entites.Position;
import com.vti.entites.PositionName;
import com.vti.untils.HibernateUntil;

@Repository
public class PositionRepository implements IPositionRepository {

	@Override
	public List<Position> getAllPosition() {
		Session session = null;
		try {
			session = HibernateUntil.getFactory().openSession();
			String hqlquery = "FROM Position";
			Query<Position> query = session.createQuery(hqlquery);
			List<Position> listPositions = query.list();
			return listPositions;
		} finally {
			if (session != null) {
			}
			session.close();
		}
	}

	@Override
	public Position getPositionById(int id) {
		Session session = null;
		try {
			session = HibernateUntil.getFactory().openSession();
			String hqlquery = "FROM Position P WHERE P.id = " + id;
			Query<Position> query = session.createQuery(hqlquery);
			Position position = query.uniqueResult();
			System.out.println(position.toString());
			return position;
		} finally {
			if (session != null) {
				session.close();
			}

		}
	}

	@Override
	public Position createPosition(String name) {
		Session session = null;
		try {
			session = HibernateUntil.getFactory().openSession();
			Position position = new Position();
			session.save(position);
			System.out.println("Khởi tạo thành công");
			return position;
		} finally {
			if (session != null) {
				session.close();
			}

		}
	}

	@Override
	public Position updatePosition(int id, String name) {
		Session session = HibernateUntil.getFactory().openSession();
		session.beginTransaction();
		String hqlquery = "FROM Póition P WHERE P.id = " + id;
		Query<Position> query = session.createQuery(hqlquery);
		Position position = query.uniqueResult();
		if (position == null) {
			System.err.println("Không tìm thấy Position phù hợp");
			return null;
		}

		if (name != null) {
//			position.setPositionName(PositionName.class,name);
		}
		session.save(position);
		session.getTransaction().commit();
		session.close();
		return position;
	}

	@Override
	public Position deletePositionById(int id) {
		Session session = null;
		try {
			session = HibernateUntil.getFactory().openSession();
			Position position = session.get(Position.class, id);
			session.beginTransaction();
			session.delete(position);
			session.getTransaction().commit();
			return position;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

}
