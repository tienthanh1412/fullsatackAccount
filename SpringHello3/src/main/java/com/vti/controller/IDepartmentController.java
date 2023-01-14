package com.vti.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.vti.entites.Department;
import com.vti.entites.DTO.DepartmentDTO;
import com.vti.entites.Form.DepartmentCreateForm;

public interface IDepartmentController {

	public List<DepartmentDTO> getAllDepartment();

	public Object getDepartmentById(int id);

	public ResponseEntity<?> deleteDepartmentById(int id);

	public ResponseEntity<?> createDepartment(DepartmentCreateForm form);

	ResponseEntity<?> updateDepartment(int id, DepartmentCreateForm form);
}
