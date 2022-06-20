package sdb.server.auth.contracts;

import sdb.server.auth.entities.AppUser;

public interface UserPersistenceService {
    AppUser saveUser(AppUser user);
    AppUser findByUserName(String username);
}
