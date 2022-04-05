package asia.ncc.erp.projects.service.mapper;

import asia.ncc.erp.projects.domain.Project;
import asia.ncc.erp.projects.service.dto.ProjectDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Project} and its DTO {@link ProjectDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProjectMapper extends EntityMapper<ProjectDTO, Project> {}
