package com.playready.PlayReadyBackend.controler;

import com.playready.PlayReadyBackend.dto.ProductDto;
import com.playready.PlayReadyBackend.dto.UserDto;
import com.playready.PlayReadyBackend.model.User;
import com.playready.PlayReadyBackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserControler {


    private final UserService userService;

    public UserControler(UserService userService) {

        this.userService = userService;

    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserDto userDto) {
        User user = userService.createUser(userDto);
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/" + user.getUsername()).toUriString());
        return ResponseEntity.created(uri).body("Succes");
    }

    @PostMapping("/{id}/requestedProducts")
    public ResponseEntity<UserDto> requestProduct(@RequestBody ProductDto productDto, @PathVariable String id) {
        return ResponseEntity.ok(userService.addRequestedProduct(id, productDto));
    }

    @GetMapping("/{id}/requestedProducts")
    public ResponseEntity<List<ProductDto>> getRequestedProducts(@PathVariable String id) {
        return ResponseEntity.ok(userService.getRequestedProducts(id));
    }


    @GetMapping
    public ResponseEntity<Iterable<UserDto>> getUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUsers(@PathVariable String id) {
        return ResponseEntity.ok(userService.getUsers(id));
    }
}