package sdb.server.auth.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sdb.server.auth.config.UserMapper;
import sdb.server.auth.contracts.AddRoleToUserService;
import sdb.server.auth.contracts.CreateRoleService;
import sdb.server.auth.contracts.CreateUserService;
import sdb.server.auth.contracts.UserAppService;
import sdb.server.auth.dto.RoleCreateRequest;
import sdb.server.auth.dto.RoleResponse;
import sdb.server.auth.dto.UserCreateRequest;
import sdb.server.auth.dto.UserResponse;
import sdb.server.auth.entities.AppUser;
import sdb.server.auth.entities.Role;
import sdb.server.auth.exceptions.RoleAlreadyExistsException;
import sdb.server.auth.exceptions.UserAlreadyExistsException;

@Service @RequiredArgsConstructor 
public class UserAppServiceImpl implements UserAppService {
    private final CreateUserService createUserService;
    private final CreateRoleService createRoleService;
    private final AddRoleToUserService addRoleToUserService;

    private final UserMapper mapper;

    @Override
    public UserResponse saveUser(UserCreateRequest userCreateRequest) {
        AppUser userRequest = mapper.userCreateRequestToAppUser(userCreateRequest);
        
        try{
           AppUser createdUser = createUserService.createUser(userRequest);
           return mapper.appUserToUserResponse(createdUser);
        } catch(UserAlreadyExistsException e){
            throw e;
        }
    }

    @Override
    public RoleResponse saveRole(RoleCreateRequest request) {
        Role roleRequest = mapper.roleCreateRequestToRole(request);
        
        try{
           Role createdRole = createRoleService.createRole(roleRequest);
           return mapper.roleToRoleResponse(createdRole);
        } catch(RoleAlreadyExistsException e){
            throw e;
        }
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        try{
            addRoleToUserService.addRoleToUser(username, roleName);
        } catch(RuntimeException e){
            throw e;
        }
    }

    @Override
    public UserResponse getUser(String username) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<UserResponse> getUsers(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }
}
