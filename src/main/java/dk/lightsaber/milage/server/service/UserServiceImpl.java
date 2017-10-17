package dk.lightsaber.milage.server.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
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
	private IUserJpaRepository userRepo;
	private Logger log;
	UserDtoPdoConverter converter;

	public UserServiceImpl(IUserJpaRepository userRepo, Logger log) {
		this.userRepo = userRepo;
		this.log = log;
		this.converter = new UserDtoPdoConverter();
	}
	
	@Transactional
	@Override
	public List<UserDto> getAllRecords() {
		log.debug("### public List<UserDto> getAllRecords()" );
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
