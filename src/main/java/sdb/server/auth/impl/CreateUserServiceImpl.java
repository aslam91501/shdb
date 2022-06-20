package sdb.server.auth.serviceImpl;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sdb.server.auth.entities.AppUser;
import sdb.server.auth.exceptions.UserAlreadyExistsException;
import sdb.server.auth.services.CreateUserService;
import sdb.server.auth.services.UserPersistenceService;

@Service @RequiredArgsConstructor
public class CreateUserServiceImpl implements CreateUserService {
    private final UserPersistenceService userPersistenceService;

    @Override
    public AppUser createUser(AppUser user) {
        if(userPersistenceService.findByUserName(user.getUsername()) == null)
            return userPersistenceService.saveUser(user);
        else throw new UserAlreadyExistsException();
    }

   
}
