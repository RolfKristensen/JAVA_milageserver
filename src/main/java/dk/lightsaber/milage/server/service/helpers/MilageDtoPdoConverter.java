package dk.lightsaber.milage.server.service.helpers;

import dk.lightsaber.milage.server.pdo.model.MilagePdo;
import dk.lightsaber.milage.server.service.dto.model.MilageDto;

import java.util.List;

public class MilageDtoPdoConverter implements DtoPdoConverter<MilageDto, MilagePdo> {
	@Override
	public MilagePdo convertToPdo(MilageDto milageDto) {
		return null;
	}

	@Override
	public MilageDto convertToDto(MilagePdo milagePdo) {
		return null;
	}

	@Override
	public List<MilagePdo> convertToPdoList(List<MilageDto> milageDtos) {
		return null;
	}

	@Override
	public List<MilageDto> convertToDtoList(List<MilagePdo> milagePdos) {
		return null;
	}
}
