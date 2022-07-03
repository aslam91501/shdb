package sdb.server.auth.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String username;
    private List<RoleResponse> roles;

    public UserResponse() {
        roles = new ArrayList<RoleResponse>();
    }
}
