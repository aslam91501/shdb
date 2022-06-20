package sdb.server.auth.contracts;

import sdb.server.auth.entities.Role;

public interface RolePersistenceService {
    Role saveRole(Role role);
    Role getRoleByName(String name);
}
