package sdb.server.auth.serviceImpl;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sdb.server.auth.entities.AppUser;
import sdb.server.auth.repo.UserRepository;
import sdb.server.auth.services.UserPersistenceService;

@Service @RequiredArgsConstructor @Transactional(value = TxType.REQUIRES_NEW)
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
