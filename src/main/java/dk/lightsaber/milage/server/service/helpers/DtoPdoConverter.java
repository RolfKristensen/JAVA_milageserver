package dk.lightsaber.milage.server.service.helpers;

import java.util.List;

public interface DtoPdoConverter<DTO, PDO> {
	public PDO convertToPdo(DTO dto);
	public DTO convertToDto(PDO pdo);
	public List<PDO> convertToPdoList(List<DTO> dtos);
	public List<DTO> convertToDtoList(List<PDO> pdos);
}
