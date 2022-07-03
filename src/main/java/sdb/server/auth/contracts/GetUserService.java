package sdb.server.auth.contracts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import sdb.server.auth.entities.AppUser;

public interface GetUserService {
    AppUser getUser(String username);
    Page<AppUser> getUsersPaged(Pageable pageable);
}
