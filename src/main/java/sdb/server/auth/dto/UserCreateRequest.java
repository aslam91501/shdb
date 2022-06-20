package sdb.server.auth.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UserCreateRequest {
    @NotNull
    private String username;
    
    @NotNull
    private String password;
}
