package dk.lightsaber.milage.server.service;

import java.util.List;

import dk.lightsaber.milage.server.service.dto.model.MilageDto;

public interface IMilageService {
	List<MilageDto> findAll();

}