package com.vti.entites.DTO;

import com.vti.entites.Department;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class DepartmentDTO {
	private int id;
	private String name;
	
	public DepartmentDTO(Department department) {
		this.id=department.getId();
		this.name=department.getName();
	}
}
