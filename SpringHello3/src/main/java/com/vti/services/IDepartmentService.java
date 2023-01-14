package com.vti.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.vti.entites.Department;
import com.vti.entites.Form.DepartmentCreateForm;

public interface IDepartmentService {

	public List<Department> getAllDepartment();

	public Department getDepartmentById(int id) throws Exception;

	public Department deleteDepartmentById(int id) throws Exception;

	public Department createDepartment(DepartmentCreateForm form) throws Exception;

	public Department updateDepartment(int id, DepartmentCreateForm form) throws Exception;

	public Page<Department> getAllDepartment(Pageable pageable);

}
