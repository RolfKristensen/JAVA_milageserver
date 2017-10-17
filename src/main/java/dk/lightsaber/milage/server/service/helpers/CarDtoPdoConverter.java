package dk.lightsaber.milage.server.service.helpers;

import java.util.ArrayList;
import java.util.List;

import dk.lightsaber.milage.server.pdo.model.CarPdo;
import dk.lightsaber.milage.server.service.dto.model.CarDto;

public class CarDtoPdoConverter implements DtoPdoConverter<CarDto, CarPdo> {

	@Override
	public CarPdo convertToPdo(CarDto dto) {
		CarPdo pdo = (CarPdo) new CarPdo()
						.setFuel_type(dto.getFuel_type())
						.setMake(dto.getMake())
						.setModel(dto.getModel())
						.setModel_specific(dto.getModel_specific())
						.setName(dto.getName())
						.setNominated_mixed_milage(dto.getNominated_mixed_milage())
						.setId(dto.getId());

		return pdo;
	}

	@Override
	public CarDto convertToDto(CarPdo pdo) {
		CarDto dto = new CarDto()
						.setFuel_type(pdo.getFuel_type())
						.setMake(pdo.getMake())
						.setModel(pdo.getModel())
						.setModel_specific(pdo.getModel_specific())
						.setName(pdo.getName())
						.setNominated_mixed_milage(pdo.getNominated_mixed_milage())
						.setId(pdo.getId());
		return dto;
	}

	@Override
	public List<CarPdo> convertToPdoList(List<CarDto> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CarDto> convertToDtoList(List<CarPdo> pdos) {
		List<CarDto> carDtos = new ArrayList<>(pdos.size());
		
		for(CarPdo pdo : pdos) {
			CarDto dto = convertToDto(pdo);
			carDtos.add(dto);
		}
		
		return carDtos;
	}

}
