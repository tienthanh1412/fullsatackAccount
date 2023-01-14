package com.vti.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.vti.entites.Department;
import com.vti.untils.HibernateUntil;

@Repository
public class DepartmentRepository implements IDepartmentRepository {

	@Override
	public List<Department> getAllDepartment() {
		Session session = null;
		try {
			session = HibernateUntil.getFactory().openSession();
			String hqlquery = "FROM Department";
			Query<Department> query = session.createQuery(hqlquery);
			List<Department> listDepartments = query.list();
			return listDepartments;
		} finally {
			if (session != null) {
			}
			session.close();
		}
	}

	@Override
	public Department getDepartmentById(int id) {
		Session session = null;
		try {
			session = HibernateUntil.getFactory().openSession();
			String hqlquery = "FROM Department D WHERE D.id = " + id;
			Query<Department> query = session.createQuery(hqlquery);
			Department department = query.uniqueResult();
			return department;
		} finally {
			if (session != null) {
				session.close();
			}

		}

	}

	@Override
	public Department createDepartment(Department department) {
		Session session = null;
		try {
			session = HibernateUntil.getFactory().openSession();
			session.beginTransaction();
			session.save(department);
			session.getTransaction().commit();
			return department;
		} finally {
			if (session != null) {
				session.close();
			}

		}

	}

	@Override
	public Department updateDepartment(Department department) {
		Session session = null;
		try {
			session = HibernateUntil.getFactory().openSession();
			session.beginTransaction();
			session.update(department);
			session.getTransaction().commit();
			return department;
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public Department deleteDepartmentById(int id) {
		Session session = null;
		try {
			session = HibernateUntil.getFactory().openSession();
			Department department = session.get(Department.class, id);
			session.beginTransaction();
			session.delete(department);
			session.getTransaction().commit();
			return department;
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

}
