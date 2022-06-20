package sdb.server.auth.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import sdb.server.auth.dto.UserCreateRequest;
import sdb.server.auth.dto.UserResponse;
import sdb.server.auth.services.UserAppService;

@RestController @RequestMapping("api/auth") @RequiredArgsConstructor
public class UserController {
    private final UserAppService userAppService;

    @PostMapping("register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody @Valid UserCreateRequest request){
        UserResponse createdUser = userAppService.saveUser(request);
        return new ResponseEntity<UserResponse>(createdUser, HttpStatus.CREATED);
    }
}
