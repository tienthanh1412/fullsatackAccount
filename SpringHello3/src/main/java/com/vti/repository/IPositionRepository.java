package com.vti.repository;

import java.util.List;

import com.vti.entites.Department;
import com.vti.entites.Position;

public interface IPositionRepository {

	List<Position> getAllPosition();

	Position getPositionById(int id);

	Position createPosition(String name);

	Position updatePosition(int id, String name);

	Position deletePositionById(int id);

}
