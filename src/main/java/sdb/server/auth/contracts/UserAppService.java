package sdb.server.auth.contracts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import sdb.server.auth.dto.RoleCreateRequest;
import sdb.server.auth.dto.RoleResponse;
import sdb.server.auth.dto.UserCreateRequest;
import sdb.server.auth.dto.UserResponse;

public interface UserAppService {
    UserResponse saveUser(UserCreateRequest userCreateRequest);
    RoleResponse saveRole(RoleCreateRequest role);
    void addRoleToUser(String username, String roleName);
    UserResponse getUser(String username);
    Page<UserResponse> getUsers(Pageable pageable);
}
