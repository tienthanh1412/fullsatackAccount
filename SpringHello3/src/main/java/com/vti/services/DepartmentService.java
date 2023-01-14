package com.vti.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vti.entites.Department;
import com.vti.entites.Form.DepartmentCreateForm;
import com.vti.repository.DepartmentRepository;
import com.vti.repository.IDepartmentRepository;
import com.vti.repository.IDepartmentRepositoryV2;

@Service
public class DepartmentService implements IDepartmentService {
	@Autowired
	private IDepartmentRepository departmentRepository;

	@Autowired
	private IDepartmentRepositoryV2 departmentRepositoryV2;
	
	public DepartmentService() {
	}

	@Override
	public List<Department> getAllDepartment() {

		return departmentRepository.getAllDepartment();

	}

	@Override
	public Department getDepartmentById(int id) throws Exception {
		if (id < 1) {
			throw new Exception("ID Không Hợp lệ");
		}
		return departmentRepository.getDepartmentById(id);
	}

	@Override
	public Department createDepartment(DepartmentCreateForm form) {
		Department department = new Department();
		if (form.getName() != null) {
			department.setName(form.getName());
		}

		departmentRepository.createDepartment(department);
		return department;
	}

	@Override
	public Department updateDepartment(int id, DepartmentCreateForm form)  {
		Department department = departmentRepository.getDepartmentById(id);

	
		if(form.getName() != null) {
			department.setName(form.getName());
		}
		
		departmentRepository.updateDepartment(department);
		return department;
	}

	@Override
	public Department deleteDepartmentById(int id) throws Exception {
		if (id < 0) {
			throw new Exception("ID Không Hợp Lệ");
		}
		return departmentRepository.deleteDepartmentById(id);
	}

	@Override
	public Page<Department> getAllDepartment(Pageable pageable) {
		
		return departmentRepositoryV2.findAll(pageable);
	}

}
