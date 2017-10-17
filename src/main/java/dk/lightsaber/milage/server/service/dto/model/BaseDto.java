package dk.lightsaber.milage.server.service.dto.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseDto {
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();
		String result = null;
		try {
			result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}
