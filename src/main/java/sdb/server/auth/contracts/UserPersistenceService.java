package sdb.server.auth.contracts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import sdb.server.auth.entities.AppUser;

public interface UserPersistenceService {
    AppUser saveUser(AppUser user);
    AppUser findByUserName(String username);
    Page<AppUser> getUsers(Pageable pageable);
}
