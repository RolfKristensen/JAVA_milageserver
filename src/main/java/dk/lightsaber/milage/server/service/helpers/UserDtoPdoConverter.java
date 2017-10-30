package dk.lightsaber.milage.server.service.helpers;

import java.util.ArrayList;
import java.util.List;

import dk.lightsaber.milage.server.pdo.model.UserPdo;
import dk.lightsaber.milage.server.service.dto.model.UserDto;

public class UserDtoPdoConverter implements DtoPdoConverter<UserDto, UserPdo> {

	@Override
	public UserPdo convertToPdo(UserDto dto) {
		UserPdo pdo = (UserPdo) new UserPdo()
				.setEmail(dto.getEmail())
				.setFirstName(dto.getFirstName())
				.setLastName(dto.getLastName())
				.setId(dto.getId());
		return pdo;
	}

	@Override
	public UserDto convertToDto(UserPdo pdo) {
		UserDto dto = new UserDto()
				.setEmail(pdo.getEmail())
				.setFirstName(pdo.getFirstName())
				.setId(pdo.getId())
				.setLastName(pdo.getLastName());
		dto.setAuthToken(pdo.getAuthToken());

		CarDtoPdoConverter carConv = new CarDtoPdoConverter();

		dto.setCars(carConv.convertToDtoList(pdo.getCars()));

		return dto;
	}

	@Override
	public List<UserPdo> convertToPdoList(List<UserDto> dtos) {
		return null;
	}

	@Override
	public List<UserDto> convertToDtoList(List<UserPdo> pdos) {
		List<UserDto> userDtos = new ArrayList<>(pdos.size());
		
		for(UserPdo pdo : pdos) {
			UserDto dto = convertToDto(pdo);
			userDtos.add(dto);
		}
		
		return userDtos;
	}

}
