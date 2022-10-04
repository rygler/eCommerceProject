package com.example.ecommerceproject.controller;

import com.example.ecommerceproject.model.persistence.Cart;
import com.example.ecommerceproject.model.persistence.User;
import com.example.ecommerceproject.model.persistence.repositories.CartRepository;
import com.example.ecommerceproject.model.persistence.repositories.UserRepository;
import com.example.ecommerceproject.model.requests.CreateUserRequest;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    public static final int MINIMUM_PASSWORD_LENGTH = 7;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/id/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        return ResponseEntity.of(userRepository.findById(id));
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> findByUserName(@PathVariable String username) {
        User user = userRepository.findByUsername(username);
        return user == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(user);
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequest createUserRequest) {
        if(isPasswordTooShort(createUserRequest) || arePasswordsDifferent(createUserRequest)){
            return ResponseEntity.badRequest().build();
        }
        User user = initializeUser(createUserRequest);
        Cart cart = new Cart();
        cartRepository.save(cart);
        user.setCart(cart);
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @NotNull
    private User initializeUser(CreateUserRequest createUserRequest) {
        log.info("User name set with " + createUserRequest.getUsername());
        return new User()
                .setPassword(bCryptPasswordEncoder.encode(createUserRequest.getPassword()))
                .setUsername(createUserRequest.getUsername());
    }

    private boolean arePasswordsDifferent(CreateUserRequest createUserRequest) {
        return !createUserRequest.getPassword().equals(createUserRequest.getConfirmPassword());
    }

    private boolean isPasswordTooShort(CreateUserRequest createUserRequest) {
        return createUserRequest.getPassword().length() < MINIMUM_PASSWORD_LENGTH;
    }
}
