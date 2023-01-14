package com.vti.controller;

import java.util.List;

import com.vti.entites.Department;
import com.vti.entites.Position;

public interface IPositionController {
	List<Position> getAllPosition();

	Position getPositionById(int id);

	Position createPosition(String name);

	Position updatePosition(int id, String name);

	Position deletePositionById(int id);

}
