package sdb.server.auth.impl;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sdb.server.auth.contracts.UserPersistenceService;
import sdb.server.auth.entities.AppUser;
import sdb.server.auth.repo.UserRepository;

@Service @RequiredArgsConstructor
public class UserPersistenceServiceImpl implements UserPersistenceService{
    private final UserRepository userRepository;
    
    @Override
    public AppUser saveUser(AppUser user) {
        return userRepository.save(user);
    }

    @Override
    public AppUser findByUserName(String username) {
        return userRepository.findByUsername(username);
    }
    
}
