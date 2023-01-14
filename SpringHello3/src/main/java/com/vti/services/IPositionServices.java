package com.vti.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vti.entites.Department;
import com.vti.entites.Position;

public interface IPositionServices {

	List<Position> getAllPosition();

	Position getPositionById(int id);

	Position createPosition(String name);

	Position updatePosition(int id, String name);

	Position deletePositionById(int id);

	Page<Position> getAllPositions(Pageable pageable);

}
