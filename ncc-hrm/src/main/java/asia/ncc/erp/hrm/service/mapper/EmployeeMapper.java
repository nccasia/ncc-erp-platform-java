package asia.ncc.erp.hrm.service.mapper;

import asia.ncc.erp.hrm.domain.Employee;
import asia.ncc.erp.hrm.service.dto.EmployeeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Employee} and its DTO {@link EmployeeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EmployeeMapper extends EntityMapper<EmployeeDTO, Employee> {}
