package sdb.server.auth.contracts;

import sdb.server.auth.entities.Role;

public interface CreateRoleService {
    Role createRole(Role roleRequest);
}
