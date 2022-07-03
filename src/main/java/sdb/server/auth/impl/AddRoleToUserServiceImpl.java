package sdb.server.auth.impl;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sdb.server.auth.contracts.AddRoleToUserService;
import sdb.server.auth.contracts.RolePersistenceService;
import sdb.server.auth.contracts.UserPersistenceService;
import sdb.server.auth.entities.AppUser;
import sdb.server.auth.entities.Role;
import sdb.server.auth.exceptions.RoleDoesNotExistException;
import sdb.server.auth.exceptions.UserDoesNotExistException;

@Service @RequiredArgsConstructor @Transactional(value = TxType.REQUIRES_NEW)
public class AddRoleToUserServiceImpl implements AddRoleToUserService {
    private final UserPersistenceService userPersistenceService;
    private final RolePersistenceService rolePersistenceService;

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser user;
        Role role;

        // Check user exists
        user = userPersistenceService.findByUserName(username);
        if(user == null) throw new UserDoesNotExistException();
        
        // Check role exists
        role = rolePersistenceService.getRoleByName(roleName);
        if(role == null) throw new RoleDoesNotExistException();
        
        // Check if user already contains role and save
        if(!user.getRoles().contains(role)){
            user.getRoles().add(role);
            userPersistenceService.saveUser(user);
        }
    }
    
}
