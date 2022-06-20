package sdb.server.auth.impl;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sdb.server.auth.contracts.RolePersistenceService;
import sdb.server.auth.entities.Role;
import sdb.server.auth.repo.RoleRepository;

@Service @RequiredArgsConstructor 
public class RolePersistenceServiceImpl implements RolePersistenceService {
    private final RoleRepository roleRepository;

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findByName(name);
    }
    
}
