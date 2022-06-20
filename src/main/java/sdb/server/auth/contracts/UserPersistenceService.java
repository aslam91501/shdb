package sdb.server.auth.services;

import sdb.server.auth.entities.AppUser;

public interface UserPersistenceService {
    AppUser saveUser(AppUser user);
    AppUser findByUserName(String username);
}
