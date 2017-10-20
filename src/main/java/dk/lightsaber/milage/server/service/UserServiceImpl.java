package dk.lightsaber.milage.server.service;

import java.util.List;

import javax.transaction.Transactional;

import dk.lightsaber.milage.server.config.Default;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dk.lightsaber.milage.server.pdo.model.CarPdo;
import dk.lightsaber.milage.server.pdo.model.UserPdo;
import dk.lightsaber.milage.server.pdo.repository.IUserJpaRepository;
import dk.lightsaber.milage.server.service.dto.model.CarDto;
import dk.lightsaber.milage.server.service.dto.model.UserChangePasswordDto;
import dk.lightsaber.milage.server.service.dto.model.UserCredentialsDto;
import dk.lightsaber.milage.server.service.dto.model.UserDto;
import dk.lightsaber.milage.server.service.helpers.CarDtoPdoConverter;
import dk.lightsaber.milage.server.service.helpers.UserDtoPdoConverter;

@Service
public class UserServiceImpl implements IUserService {
	private final static Logger LOGGER = LoggerFactory.getLogger(Default.class.getName());

	private IUserJpaRepository userRepo;
	UserDtoPdoConverter converter;

	public UserServiceImpl(IUserJpaRepository userRepo) {
		this.userRepo = userRepo;
		this.converter = new UserDtoPdoConverter();
	}
	
	@Transactional
	@Override
	public List<UserDto> getAllRecords() {
		LOGGER.debug("### public List<UserDto> getAllRecords()" );
		return converter.convertToDtoList(userRepo.findAll());
	}

	@Transactional
	@Override
	public UserDto getById(long id) {
		UserPdo pdo = userRepo.findOne(id);
		return converter.convertToDto(pdo);
	}

	@Transactional
	@Override
	public UserDto login(UserCredentialsDto uc) {
		return null;
	}

	@Transactional
	@Override
	public boolean changePassword(UserChangePasswordDto ucp) {
		// TODO Auto-generated method stub
		return false;
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
		UserPdo pdo = converter.convertToPdo(user);
		pdo = userRepo.save(pdo);
		return pdo.getId();
	}


}
