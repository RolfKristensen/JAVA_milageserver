package dk.lightsaber.milage.server.service;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import dk.lightsaber.milage.server.config.Default;
import dk.lightsaber.milage.server.service.dto.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import dk.lightsaber.milage.server.pdo.model.CarPdo;
import dk.lightsaber.milage.server.pdo.model.UserPdo;
import dk.lightsaber.milage.server.pdo.repository.IUserJpaRepository;
import dk.lightsaber.milage.server.service.helpers.CarDtoPdoConverter;
import dk.lightsaber.milage.server.service.helpers.UserDtoPdoConverter;

@Service
public class UserServiceImpl implements IUserService {
	private final static Logger LOGGER = LoggerFactory.getLogger(Default.class.getName());
	public static final String INVALID_AUTH_TOKEN = "Invalid authToken";

	private IUserJpaRepository userRepo;
	private final UserDtoPdoConverter userConverter;
	private final CarDtoPdoConverter carConverter;

	public UserServiceImpl(IUserJpaRepository userRepo) {
		this.userRepo = userRepo;
		this.userConverter = new UserDtoPdoConverter();
		this.carConverter = new CarDtoPdoConverter();
	}
	
	@Transactional
	@Override
	public List<UserDto> getAllRecords() {
		LOGGER.debug("### public List<UserDto> getAllRecords()" );
		return userConverter.convertToDtoList(userRepo.findAll());
	}

	@Transactional
	@Override
	public UserDto getById(long id) {
		UserPdo pdo = userRepo.findOne(id);
		return userConverter.convertToDto(pdo);
	}

	@Transactional
	@Override
	public UserDto login(UserCredentialsDto uc) {
		UserPdo pdo = userRepo.findByUsername(uc.getUserName(), uc.getPassword());
		if (pdo != null) {
			return userConverter.convertToDto(pdo);
		}
		return null;
	}

	@Transactional
	@Override
	public boolean changePassword(UserChangePasswordDto ucp) {
		UserPdo pdo = userRepo.findByUsername(ucp.getUserName(), ucp.getOldPassword());
		if (pdo != null) {
			pdo.setPassword(ucp.getNewPassword());
			userRepo.save(pdo);
			return true;
		} else {
			return false;
		}
	}

	@Transactional
	@Override
	public List<CarDto> getListOfCarsForUserId(long id) {
		List<CarPdo> pCars = userRepo.getOne(id).getCars();
		return new CarDtoPdoConverter().convertToDtoList(pCars);
	}

	@Override
	public Long create(UserDto user) {
		String errorMsg = null;
		if (user.getFirstName() == null) {
			errorMsg = "Firstname must be defined";
		} else if (user.getLastName() == null) {
			errorMsg = "Lastname must be defined";
		} else if (user.getPassword() == null) {
			errorMsg = "Password must be defined";
		} else if (user.getEmail() == null) {
			errorMsg = "Email must be defined";
		} else if (user.getId() != 0) {
			errorMsg = "UserId cannot be defined for new resources";
		} if (userRepo.findAll().stream().filter(
				pdo -> user.getEmail().equals(pdo.getEmail())).count() > 0) {
			errorMsg = "User all ready exists with the given email";
		}

		if (errorMsg != null) {
			throw new RuntimeException(errorMsg);
		}
		UserPdo pdo = userConverter.convertToPdo(user);
		pdo = userRepo.save(pdo);
		return pdo.getId();
	}

	@Override
	public String createUserAuthToken(UserCredentialsDto uc) {
		UserPdo user = userRepo.findByUsername(uc.getUserName(), uc.getPassword());
		if (user == null) {
			throw new IllegalArgumentException("Username or Password is incorrect");
		}
		String token = UUID.randomUUID().toString();
		user.setAuthToken(token);
		userRepo.save(user);

		return token;
	}

	@Override
	public UserDto getUserInfo(AuthToken token) {
		UserPdo pdo = userRepo.findByToken(token.getToken());
		if (pdo == null) {
			throw new IllegalArgumentException(INVALID_AUTH_TOKEN);
		}
		return userConverter.convertToDto(pdo);
	}

	@Override
	public boolean changePassword(AuthToken token, String password) {
		UserPdo pdo = userRepo.findByToken(token.getToken());
		if (pdo == null) {
			throw new IllegalArgumentException(INVALID_AUTH_TOKEN);
		}
		pdo.setPassword(password);
		userRepo.save(pdo);
		return true;
	}

	@Override
	public List<CarDto> getListOfCars(AuthToken token) {
		List<CarDto> cars;

		UserPdo user = userRepo.findByToken(token.getToken());
		if (user == null) {
			throw new IllegalArgumentException(INVALID_AUTH_TOKEN);
		}
		cars = carConverter.convertToDtoList(user.getCars());
		return cars;
	}

}
