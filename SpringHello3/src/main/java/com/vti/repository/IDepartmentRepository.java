package com.vti.repository;

import java.util.List;

import com.vti.entites.Department;

public interface IDepartmentRepository {

	public List<Department> getAllDepartment();

	public Department getDepartmentById(int id);

	public Department deleteDepartmentById(int id);

	public Department createDepartment(Department department);

	public Department updateDepartment(Department department);

}
