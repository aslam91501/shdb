package sdb.server.auth.contracts;

import sdb.server.auth.entities.AppUser;

public interface CreateUserService {
    AppUser createUser(AppUser user);
}
