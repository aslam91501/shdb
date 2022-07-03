package sdb.server.auth.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sdb.server.auth.contracts.GetUserService;
import sdb.server.auth.contracts.UserPersistenceService;
import sdb.server.auth.entities.AppUser;
import sdb.server.auth.exceptions.UserDoesNotExistException;

@Service @RequiredArgsConstructor
public class GetUserServiceImpl implements GetUserService{
    private final UserPersistenceService userPersistenceService;

    @Override
    public AppUser getUser(String username) {
        AppUser user = userPersistenceService.findByUserName(username);

        if(user == null) throw new UserDoesNotExistException();
        else return user;
    }

    @Override
    public Page<AppUser> getUsersPaged(Pageable pageable) {
        return userPersistenceService.getUsers(pageable);
    }
    
}
