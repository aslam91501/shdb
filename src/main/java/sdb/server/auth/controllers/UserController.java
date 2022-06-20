package sdb.server.auth.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import sdb.server.auth.contracts.UserAppService;
import sdb.server.auth.dto.AddRoleToUserRequest;
import sdb.server.auth.dto.RoleCreateRequest;
import sdb.server.auth.dto.RoleResponse;
import sdb.server.auth.dto.UserCreateRequest;
import sdb.server.auth.dto.UserResponse;

@RestController @RequestMapping("api/auth") @RequiredArgsConstructor
public class UserController {
    private final UserAppService userAppService;

    @PostMapping("user/new")
    public ResponseEntity<UserResponse> registerUser(@RequestBody @Valid UserCreateRequest request){
        UserResponse createdUser = userAppService.saveUser(request);
        return new ResponseEntity<UserResponse>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("user/addrole")
    public ResponseEntity<?> addRoleToUser(@RequestBody @Valid AddRoleToUserRequest request){
        userAppService.addRoleToUser(request.getUsername(), request.getRoleName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("role/new")
    public ResponseEntity<RoleResponse> createRole(@RequestBody @Valid RoleCreateRequest request){
        RoleResponse createdRole = userAppService.saveRole(request);
        return new ResponseEntity<RoleResponse>(createdRole, HttpStatus.CREATED);
    }

}
