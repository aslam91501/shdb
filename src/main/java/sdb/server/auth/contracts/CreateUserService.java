package sdb.server.auth.services;

import sdb.server.auth.entities.AppUser;

public interface CreateUserService {
    AppUser createUser(AppUser user);
}
