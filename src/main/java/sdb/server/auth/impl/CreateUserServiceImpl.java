package sdb.server.auth.impl;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sdb.server.auth.contracts.CreateUserService;
import sdb.server.auth.contracts.UserPersistenceService;
import sdb.server.auth.entities.AppUser;
import sdb.server.auth.exceptions.UserAlreadyExistsException;

@Service @RequiredArgsConstructor @Transactional(value = TxType.REQUIRES_NEW)
public class CreateUserServiceImpl implements CreateUserService {
    private final UserPersistenceService userPersistenceService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public AppUser createUser(AppUser user) {
        if(userPersistenceService.findByUserName(user.getUsername()) == null){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userPersistenceService.saveUser(user);
        }
        else throw new UserAlreadyExistsException();
    }

   
}
