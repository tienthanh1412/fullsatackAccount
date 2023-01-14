package com.vti.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vti.entites.Position;
import com.vti.repository.IPositionRepository;
import com.vti.repository.IPositionRepositoryV2;

@Service
public class PositionService implements IPositionServices {
	@Autowired
	private IPositionRepository positionRepository;

	@Autowired
	private IPositionRepositoryV2 iPositionRepositoryV2;
	@Override
	public List<Position> getAllPosition() {

		return positionRepository.getAllPosition();
	}

	@Override
	public Position getPositionById(int id) {
		return positionRepository.getPositionById(id);
	}

	@Override
	public Position createPosition(String name) {
		return positionRepository.createPosition(name);
	}

	@Override
	public Position updatePosition(int id, String name) {

		return positionRepository.updatePosition(id, name);
	}

	@Override
	public Position deletePositionById(int id) {

		return positionRepository.deletePositionById(id);
	}

	@Override
	public Page<Position> getAllPositions(Pageable pageable) {
		// TODO Auto-generated method stub
		return iPositionRepositoryV2.findAll(pageable);
	}

}
