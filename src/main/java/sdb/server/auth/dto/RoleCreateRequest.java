package sdb.server.auth.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class RoleCreateRequest {
    @NotNull
    private String name;
}
