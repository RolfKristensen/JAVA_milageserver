package dk.lightsaber.milage.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dk.lightsaber.milage.server.repository.IMilageRepository;
import dk.lightsaber.milage.server.service.dto.model.MilageDto;

@Service("iMilageService")
public class MilageServiceImpl implements IMilageService {

	private IMilageRepository repository;
	
	@Autowired
	public MilageServiceImpl(IMilageRepository repo) {
		repository = repo;
	}
	
	@Override
	public List<MilageDto> findAll() {
		return repository.findAll(0);
	}
}
