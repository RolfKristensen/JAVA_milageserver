package dk.lightsaber.milage.server.service.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class UserDto extends BaseDto {
	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String authToken;
	private List<CarDto> cars;

	public long getId() {
		return id;
	}

	public UserDto setId(long id) {
		this.id = id;
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public UserDto setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public UserDto setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public UserDto setEmail(String email) {
		this.email = email;
		return this;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public UserDto setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public List<CarDto> getCars() {
		return cars;
	}

	public UserDto setCars(List<CarDto> cars) {
		this.cars = cars;
		return this;
	}
}
