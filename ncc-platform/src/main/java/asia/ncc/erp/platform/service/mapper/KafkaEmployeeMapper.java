package asia.ncc.erp.platform.service.mapper;

import asia.ncc.erp.platformsdk.dto.KafkaEmployee;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Employee} and its DTO {@link EmployeeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface KafkaEmployeeMapper extends EntityMapper<asia.ncc.erp.platform.service.dto.EmployeeDTO, KafkaEmployee> {}
