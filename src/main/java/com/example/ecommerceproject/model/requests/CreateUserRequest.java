package com.example.ecommerceproject.model.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateUserRequest {

    @JsonProperty
    private String username;

    @JsonProperty
    private String password;

    @JsonProperty
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public CreateUserRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public CreateUserRequest setPassword(String password) {
        this.password = password;
        return this;
    }

    public CreateUserRequest setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
}
