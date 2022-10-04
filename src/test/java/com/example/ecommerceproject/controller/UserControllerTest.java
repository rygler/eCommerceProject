package com.example.ecommerceproject.controller;

import com.example.ecommerceproject.TestUtils;
import com.example.ecommerceproject.model.persistence.User;
import com.example.ecommerceproject.model.persistence.repositories.CartRepository;
import com.example.ecommerceproject.model.persistence.repositories.UserRepository;
import com.example.ecommerceproject.model.requests.CreateUserRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserControllerTest {
    private UserController userController;
    private final UserRepository userRepository = mock(UserRepository.class);
    private final CartRepository cartRepository = mock(CartRepository.class);
    private final BCryptPasswordEncoder bCryptPasswordEncoder = mock(BCryptPasswordEncoder.class);

    @Before
    public void setUp() {
        userController = new UserController();
        TestUtils.injectObjects(userController, "userRepository", userRepository);
        TestUtils.injectObjects(userController, "cartRepository", cartRepository);
        TestUtils.injectObjects(userController, "bCryptPasswordEncoder", bCryptPasswordEncoder);
    }

    @Test
    public void create_user_happy_path() throws Exception {
        when(bCryptPasswordEncoder.encode("testPassword")).thenReturn("thisIsHashed");
        CreateUserRequest createUserRequest = new CreateUserRequest()
                .setUsername("test")
                .setPassword("testPassword")
                .setConfirmPassword("testPassword");

        final ResponseEntity<User> response = userController.createUser(createUserRequest);
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatusCodeValue());

        User user = response.getBody();
        Assert.assertNotNull(user);
        Assert.assertEquals(0, user.getId());
        Assert.assertEquals("test", user.getUsername());
        Assert.assertEquals("thisIsHashed", user.getPassword());
    }

    @Test
    public void create_user_bad_path() throws Exception {
        when(bCryptPasswordEncoder.encode("testPassword")).thenReturn("thisIsHashed");
        CreateUserRequest createUserRequest = new CreateUserRequest()
                .setUsername("test")
                .setPassword("testPassword")
                .setConfirmPassword("wrong test password");

        final ResponseEntity<User> response = userController.createUser(createUserRequest);
        Assert.assertNotNull(response);
        Assert.assertEquals(400, response.getStatusCodeValue());

        User user = response.getBody();
        Assert.assertNull(user);
    }
}

