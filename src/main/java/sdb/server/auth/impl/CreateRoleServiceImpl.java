package sdb.server.auth.impl;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sdb.server.auth.contracts.CreateRoleService;
import sdb.server.auth.contracts.RolePersistenceService;
import sdb.server.auth.entities.Role;
import sdb.server.auth.exceptions.RoleAlreadyExistsException;

@Service @RequiredArgsConstructor @Transactional(value = TxType.REQUIRES_NEW)
public class CreateRoleServiceImpl implements CreateRoleService {
    private final RolePersistenceService rolePersistenceService;

    @Override
    public Role createRole(Role roleRequest) {
        if(rolePersistenceService.getRoleByName(roleRequest.getName()) == null)
            return rolePersistenceService.saveRole(roleRequest);
        else throw new RoleAlreadyExistsException();
    }
    
}
