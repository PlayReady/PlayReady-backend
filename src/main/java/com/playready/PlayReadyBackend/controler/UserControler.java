package com.playready.PlayReadyBackend.controler;

import com.playready.PlayReadyBackend.dto.UserDto;
import com.playready.PlayReadyBackend.model.Role;
import com.playready.PlayReadyBackend.model.User;
import com.playready.PlayReadyBackend.repository.RoleRepository;
import com.playready.PlayReadyBackend.repository.UserRepository;
import com.playready.PlayReadyBackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserControler {

    private final UserRepository userRepos;
    private final RoleRepository roleRepos;
    private final PasswordEncoder encoder;
    private final UserService userService;

    public UserControler(UserRepository userRepos, UserService userService, RoleRepository roleRepos, PasswordEncoder encoder) {
        this.userRepos = userRepos;
        this.userService = userService;
        this.roleRepos = roleRepos;
        this.encoder = encoder;
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserDto userDto) {
        User newUser = new User();
        newUser.setUsername(userDto.username);
        newUser.setPassword(encoder.encode(userDto.password));

        List<Role> userRoles = new ArrayList<>();
        for (String rolename : userDto.roles) {
            Optional<Role> or = roleRepos.findById("ROLE_" + rolename);
            if (or.isPresent()) {
                userRoles.add(or.get());
            }else{
                return ResponseEntity.badRequest().body("Role does not exist.");
            }

        }
        newUser.setRoles(userRoles);

        userRepos.save(newUser);
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/" + newUser.getUsername()).toUriString());
        return ResponseEntity.created(uri).body("Succes");
    }

    @GetMapping
    public ResponseEntity<Iterable<UserDto>> getUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}