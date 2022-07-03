package sdb.server.auth.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sdb.server.auth.contracts.GetUserService;
import sdb.server.auth.entities.AppUser;
import sdb.server.auth.exceptions.UserDoesNotExistException;

@Service @RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final GetUserService getUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            AppUser user = getUserService.getUser(username);

            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            });

            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);

        } catch(UserDoesNotExistException e) {
            throw new UsernameNotFoundException("No User found with that username");
        }
    }
    
}
