package dk.lightsaber.milage.server.repository;

import java.util.List;

import dk.lightsaber.milage.server.service.dto.model.MilageDto;

public interface IMilageRepository {
	public List<MilageDto> findAll();
	public List<MilageDto> findAll(int userId);
}
