package edu.bilkent.bilbilet.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.validation.constraints.Email;

@Data
@AllArgsConstructor
public class UserLogin {
    @Email
    private String email;
    private String password;
}
