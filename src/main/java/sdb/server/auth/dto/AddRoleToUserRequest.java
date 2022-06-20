package sdb.server.auth.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AddRoleToUserRequest {
    @NotNull
    private String username;
    @NotNull
    private String roleName;
}
