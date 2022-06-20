package sdb.server.auth.config;

import org.mapstruct.Mapper;

import sdb.server.auth.dto.RoleCreateRequest;
import sdb.server.auth.dto.RoleResponse;
import sdb.server.auth.dto.UserCreateRequest;
import sdb.server.auth.dto.UserResponse;
import sdb.server.auth.entities.AppUser;
import sdb.server.auth.entities.Role;

@Mapper(componentModel = "spring")
public interface UserMapper {
    AppUser userCreateRequestToAppUser(UserCreateRequest request);
    UserResponse appUserToUserResponse(AppUser user);
    Role roleCreateRequestToRole(RoleCreateRequest request);
    RoleResponse roleToRoleResponse(Role createdRole);
}
