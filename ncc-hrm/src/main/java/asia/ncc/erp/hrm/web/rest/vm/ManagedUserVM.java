package asia.ncc.erp.hrm.web.rest.vm;

import asia.ncc.erp.hrm.service.dto.AdminUserDTO;

/**
 * View Model extending the AdminUserDTO, which is meant to be used in the user management UI.
 */
public class ManagedUserVM extends AdminUserDTO {

    public ManagedUserVM() {
        // Empty constructor needed for Jackson.
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ManagedUserVM{" + super.toString() + "} ";
    }
}
