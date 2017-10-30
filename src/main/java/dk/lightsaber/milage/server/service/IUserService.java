package dk.lightsaber.milage.server.service;

import java.util.List;

import dk.lightsaber.milage.server.service.dto.model.*;

public interface IUserService {

	/* ToBe deleted */
	List<UserDto> getAllRecords();
	UserDto getById(long id);
	List<CarDto> getListOfCarsForUserId(long id);

	Long create(UserDto user);

	/* UserCredentials services */
	UserDto login(UserCredentialsDto uc);
	boolean changePassword(UserChangePasswordDto ucp);
	String createUserAuthToken(UserCredentialsDto uc);

	/* Auth token services */
	UserDto getUserInfo(AuthToken token);
	boolean changePassword(AuthToken token, String password);
	List<CarDto> getListOfCars(AuthToken token);

}
