package dk.lightsaber.milage.server.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import dk.lightsaber.milage.server.service.dto.model.MilageDto;

@Repository("iMilageRepository")
public class MilageRepositoryJpaImpl implements IMilageRepository {

	@Override
	public List<MilageDto> findAll() {
		return findAll(1);
	}

	@Override
	public List<MilageDto> findAll(int userId) {
		List<MilageDto> milages = new ArrayList<MilageDto>();
		
		MilageDto m  = new MilageDto();
		m.setCarId(1)
		.setGasStationId(1)
		.setGasTypeId(1)
		.setId(1)
		.setKmDriven(1000f)
		.setLitresFuled(50f)
		.setPriceL(9.5f)
		.setUserId(userId);
		
		milages.add(m);
		
		return milages;
	}

}
