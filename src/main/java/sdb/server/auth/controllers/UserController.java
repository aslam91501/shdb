package sdb.server.auth.controllers;

import javax.management.RuntimeErrorException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import sdb.server.auth.contracts.UserAppService;
import sdb.server.auth.dto.AddRoleToUserRequest;
import sdb.server.auth.dto.RoleCreateRequest;
import sdb.server.auth.dto.RoleResponse;
import sdb.server.auth.dto.UserCreateRequest;
import sdb.server.auth.dto.UserResponse;
import sdb.server.auth.entities.AppUser;
import sdb.server.auth.entities.Role;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


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

    @GetMapping("user/find/{username}")
    public ResponseEntity<UserResponse> findUserbyUsername(@PathVariable("username") String username){
        UserResponse userResponse = userAppService.getUser(username);
        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
    }

    @GetMapping("user/findAll")
    public ResponseEntity<Page<UserResponse>> findUsers(@RequestParam(name = "page", required = false) int page, @RequestParam(name = "size", required = false) int size){
        // page = (page != 0) ? page : 0;
        // size = (size != 0) ? size : 10;

        Page<UserResponse> users = userAppService.getUsers(PageRequest.of(0, 10));

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws StreamWriteException, DatabindException, IOException{
        String authorizationHeader = request.getHeader(AUTHORIZATION);
            if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
                try{
                    String token = authorizationHeader.substring("Bearer ".length());
                    Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                    JWTVerifier verifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = verifier.verify(token);
                    String username = decodedJWT.getSubject();
                    UserResponse user = userAppService.getUser(username);
                    

                    String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoles().stream().map(RoleResponse::getName).collect(Collectors.toList()))
                        .sign(algorithm); 
                    
                    Map<String, String> tokens = new HashMap<>();
                    tokens.put("access_token", access_token);
                
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    
                    
                } catch(Exception exception){
                    Map<String, String> error = new HashMap<>();
                    error.put("error_message", exception.getMessage());

                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), error);
                }
            }
            else{
                throw new RuntimeException("Could not get refresh token");
            }
    }
}
