package com.playready.PlayReadyBackend.security;

import com.playready.PlayReadyBackend.model.User;
import com.playready.PlayReadyBackend.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepos;

    public MyUserDetailsService(UserRepository repos) {
        this.userRepos = repos;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepos.findById(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return new MyUserDetails(user);
        }else {
            throw new UsernameNotFoundException(username);
        }
    }
}
