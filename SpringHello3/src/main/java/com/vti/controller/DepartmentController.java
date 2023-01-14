package com.vti.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.entites.Department;
import com.vti.entites.ObjectErrorResponse;
import com.vti.entites.DTO.DepartmentDTO;
import com.vti.entites.Form.DepartmentCreateForm;
import com.vti.services.DepartmentService;
import com.vti.services.IDepartmentService;

@RestController
@RequestMapping(value = "/v1/api")
@CrossOrigin(value = "*")
public class DepartmentController implements IDepartmentController {
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	ModelMapper mapper; 


//------------------------------------------------------------------------------
//@GetMapping(value = "/departments")
	public List<DepartmentDTO> getAllDepartment() {
		List<Department> list = departmentService.getAllDepartment();
		List<DepartmentDTO> lDepartmentDTOs = new ArrayList<>();
		for (Department department : list) {
			DepartmentDTO departmentDTO = new DepartmentDTO(department);
			lDepartmentDTOs.add(departmentDTO);
		}
		return lDepartmentDTOs;

	}
//------------------------------------------------------------------------------

@GetMapping(value = "/departments/{id}")
	public Object getDepartmentById(@PathVariable(value = "id") int id) {
		try {
			Department department = departmentService.getDepartmentById(id);
			DepartmentDTO departmentDTO = new DepartmentDTO(department);
			return new ResponseEntity<DepartmentDTO>(departmentDTO, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Tìm Kiếm Thất Bại", HttpStatus.BAD_REQUEST);
		}
	}
//------------------------------------------------------------------------------

@PostMapping(value = "/departments")
	public ResponseEntity<?> createDepartment(@RequestBody DepartmentCreateForm form) {
		try {
			Department departmentCreate = departmentService.createDepartment(form);
			DepartmentDTO departmentDTO = new DepartmentDTO(departmentCreate);
			return new ResponseEntity<DepartmentDTO>(departmentDTO, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(new ObjectErrorResponse("Lỗi Không Xác Định" + e.getMessage(), 100),
					HttpStatus.BAD_REQUEST);
		}
	}
//------------------------------------------------------------------------------

@PutMapping(value = "/departments/{id}")
	public ResponseEntity<?> updateDepartment(@PathVariable(value = "id") int id,
			@RequestBody DepartmentCreateForm form) {

		try {
			Department department = departmentService.updateDepartment(id, form);
			DepartmentDTO departmentDTO = new DepartmentDTO(department);
			return new ResponseEntity<DepartmentDTO>(departmentDTO, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
//------------------------------------------------------------------------------

@DeleteMapping(value = "/departments/{id}")
	public ResponseEntity<?> deleteDepartmentById(@PathVariable(value = "id") int id) {
		try {
			departmentService.deleteDepartmentById(id);
			return new ResponseEntity<String>("Delete Thành Công", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Delete Thất Bại" +e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}


@GetMapping(value = "/departments")
public Page<DepartmentDTO> getAllDepartments(Pageable pageable){
	Page<Department> dpPage = departmentService.getAllDepartment(pageable);
	List<DepartmentDTO> dto = mapper.map(dpPage.getContent(), new TypeToken<List<DepartmentDTO>>() {}.getType());
	Page<DepartmentDTO> dtoPage = new PageImpl<>(dto,pageable,dpPage.getTotalElements());
	return dtoPage;
}
}
