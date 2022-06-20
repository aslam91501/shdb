package sdb.server.auth.services;

import sdb.server.auth.entities.Role;

public interface CreateRoleService {
    Role createRole(Role roleRequest);
}
