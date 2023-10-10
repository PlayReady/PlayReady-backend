package com.playready.PlayReadyBackend.service;

import com.playready.PlayReadyBackend.dto.ProductDto;
import com.playready.PlayReadyBackend.dto.UserDto;
import com.playready.PlayReadyBackend.model.Product;
import com.playready.PlayReadyBackend.model.Role;
import com.playready.PlayReadyBackend.model.User;
import com.playready.PlayReadyBackend.repository.ProductRepository;
import com.playready.PlayReadyBackend.repository.RoleRepository;
import com.playready.PlayReadyBackend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    private final RoleRepository roleRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final ProductService productService;

    public UserService(PasswordEncoder encoder, UserRepository userRepository, RoleRepository roleRepository, ProductRepository productRepository, ProductService productService) {
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.productService = productService;
    }

    public Iterable<UserDto> getAllUsers() {
        Iterable<User> users = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();

        for (User user : users) {
            userDtoList.add(convertToDto(user));
        }
        return userDtoList;
    }

    public User createUser(UserDto userDto) {
        User newUser = convertToEntity(userDto);
        userRepository.save(newUser);
        return newUser;
    }

    public UserDto addRequestedProduct(String id, ProductDto requestProductDto) {
        Product product = new Product();
        Optional<Product> optionalProduct = productRepository.findById(requestProductDto.id);
        if (optionalProduct.isPresent()) {
            product = optionalProduct.get();
        }else{
//            return "product not found"; TODO make exception;
        }

        User user = new User();
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        }else{
//            return "user not found";TODO make exception
        }
        List<Product> products = user.getRequestedProducts();
        products.add(product);
        user.setRequestedProducts(products);
        userRepository.save(user);
        return convertToDto(user);
    }

    public List<ProductDto> getRequestedProducts(String id) {
        List<ProductDto> productDtos = new ArrayList<>();

        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            List<Product> requestedProducts = userOptional.get().getRequestedProducts();
            for (Product product : requestedProducts) {
                productDtos.add(productService.convertToDto(product));
            }
        }

        return productDtos;
    }



    private UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.username = user.getUsername();
        userDto.password = user.getPassword();
        userDto.phonenumber = user.getPhonenumber();
        userDto.email = user.getEmail();

        List<Product> products = user.getRequestedProducts();
        List<Long> productNames = new ArrayList<>();
        for (Product product : products) {
            productNames.add(product.getId());
        }
        userDto.requestedproductsid = productNames;

        List<Role> roles = user.getRoles();
        List<String> roleNames = new ArrayList<>();
        for (Role role : roles) {
            roleNames.add(role.getRolename());
        }
        userDto.roles = roleNames;

        return userDto;
    }

    private User convertToEntity(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.username);
        user.setPassword(encoder.encode(userDto.password));
        user.setPhonenumber(userDto.phonenumber);
        user.setEmail(userDto.email);

        List<Product> requestedProducts = new ArrayList<>();
        if (userDto.requestedproductsid != null) {
            for (Long productId : userDto.requestedproductsid) {
                Optional<Product> optionalProduct = productRepository.findById(productId);
                optionalProduct.ifPresent(requestedProducts::add);
            }
        }
        user.setRequestedProducts(requestedProducts);

        List<Role> userRoles = new ArrayList<>();
        if (userDto.roles != null) {
            for (String roleName : userDto.roles) {
                Optional<Role> optionalRole = roleRepository.findById("ROLE_" + roleName);
                optionalRole.ifPresent(userRoles::add);
            }
        }
        user.setRoles(userRoles);
        userRepository.save(user);
        return user;
    }


}
