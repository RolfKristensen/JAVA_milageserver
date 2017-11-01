package dk.lightsaber.milage.server.service;

import java.util.List;

import dk.lightsaber.milage.server.pdo.repository.IMilageJpaRepository;
import dk.lightsaber.milage.server.service.helpers.MilageDtoPdoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dk.lightsaber.milage.server.service.dto.model.MilageDto;

@Service("iMilageService")
public class MilageServiceImpl implements IMilageService {

	private final IMilageJpaRepository repository;
	private final MilageDtoPdoConverter milageConverter;

	public MilageServiceImpl(IMilageJpaRepository repo) {
		this.repository = repo;
		this.milageConverter = new MilageDtoPdoConverter();
	}
	
	@Override
	public List<MilageDto> findAll() {
		return milageConverter.convertToDtoList(repository.findAll());
	}
}
