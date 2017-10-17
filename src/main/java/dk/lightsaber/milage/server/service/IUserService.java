package dk.lightsaber.milage.server.service;

import java.util.List;

import dk.lightsaber.milage.server.service.dto.model.CarDto;
import dk.lightsaber.milage.server.service.dto.model.UserChangePasswordDto;
import dk.lightsaber.milage.server.service.dto.model.UserCredentialsDto;
import dk.lightsaber.milage.server.service.dto.model.UserDto;

public interface IUserService {
	public List<UserDto> getAllRecords();
	public UserDto getById(long id);
	public UserDto login(UserCredentialsDto uc);
	public boolean changePassword(UserChangePasswordDto ucp);
	public List<CarDto> getListOfCarsForUserId(long id);
	public Long create(UserDto user);
}
