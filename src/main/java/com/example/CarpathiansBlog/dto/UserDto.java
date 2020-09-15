package com.example.CarpathiansBlog.dto;

import com.example.CarpathiansBlog.validators.ValidPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDto {
    @NotNull(message = "Field \"username\" is required")
    @Size(min = 2, message = "Field \"username\" should be more than 2 symbols")
    private String username;

    @NotNull(message = "Field \"password\" is required")
    @ValidPassword
    private String password;

    @NotNull(message = "Field \"email\" is required")
    @Size(min = 3, message = "Email should be valid")
    @Email(message = "Email should be valid")
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
