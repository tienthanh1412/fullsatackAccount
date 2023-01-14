package com.vti.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.entites.Department;
import com.vti.entites.Position;
import com.vti.entites.DTO.DepartmentDTO;
import com.vti.entites.DTO.PositionDTO;
import com.vti.services.IPositionServices;
import com.vti.services.PositionService;

@RestController
@RequestMapping(value = "/v1/api")
@CrossOrigin(value = "*")
public class PositionController implements IPositionController {
	@Autowired
	private IPositionServices positionServices;

	@Autowired
	ModelMapper mapper;
	
	
	public List<Position> getAllPosition() {
		List<Position> list = positionServices.getAllPosition();
		return list;
	}

	@Override
	public Position getPositionById(int id) {
		return positionServices.getPositionById(id);
	}

	@Override
	public Position createPosition(String name) {

		return positionServices.createPosition(name);
	}

	@Override
	public Position updatePosition(int id, String name) {

		return positionServices.updatePosition(id, name);
	}

	@Override
	public Position deletePositionById(int id) {

		return positionServices.deletePositionById(id);
	}
	@GetMapping(value = "/positions")
	public Page<PositionDTO> getAllPositions(Pageable pageable){
		Page<Position> posPage = positionServices.getAllPositions(pageable);
		List<PositionDTO> dto = mapper.map(posPage.getContent(), new TypeToken<List<PositionDTO>>() {}.getType());
		Page<PositionDTO> dtoPage = new PageImpl<PositionDTO>(dto, pageable, posPage.getTotalElements());
		return dtoPage;
	} 
}
