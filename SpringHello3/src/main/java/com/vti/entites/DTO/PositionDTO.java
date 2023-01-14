package com.vti.entites.DTO;

import com.vti.entites.Position;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
public class PositionDTO {
	private int id;
	private String name;

	public PositionDTO(Position position) {
		this.id = position.getId();
		this.name = position.getPositionName().name();
	}
}
