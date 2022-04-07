package asia.ncc.erp.platform.service.mapper;

import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Employee} and its DTO {@link EmployeeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface KafkaEmployeeMapper extends EntityMapper<asia.ncc.erp.platform.service.dto.EmployeeDTO, asia.ncc.erp.platformsdk.dto.EmployeeDTO> {}
