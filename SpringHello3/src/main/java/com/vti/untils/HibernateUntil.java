package com.vti.untils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.vti.entites.Account;
import com.vti.entites.Department;
import com.vti.entites.Group;
import com.vti.entites.Position;

public class HibernateUntil {
	private final static SessionFactory FACTORY;

	static {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");// session lấy thông tin từ file config(trong thư mục resources) như thông
											// tin data tài khoản và mk,..
											//session được tạo ra từ config để mapping các objiect ở dưới

		cfg.addAnnotatedClass(Account.class);// khai bao voi session de connec voi data

		cfg.addAnnotatedClass(Position.class);

		cfg.addAnnotatedClass(Department.class);

		cfg.addAnnotatedClass(Group.class);

		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();

		FACTORY = cfg.buildSessionFactory(registry);
	}

	public static SessionFactory getFactory() {
		return FACTORY;
	}
}
