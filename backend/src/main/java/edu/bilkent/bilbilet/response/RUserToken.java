package edu.bilkent.bilbilet.response;

import java.util.UUID;

import edu.bilkent.bilbilet.enums.UserType;
import edu.bilkent.bilbilet.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.Data;

@Data
public class RUserToken {
    @Id
    private int id;
    private Long bilkentId;
    private String name;
    private String surname;
    private String email;
    private String telephone;
    private UserType userType;
    private String accessToken;
    private String refreshToken;

    public RUserToken(User user, String accessToken, String refreshToken) {
        this.id = user.getUserId();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.email = user.getEmail();
        this.userType = user.getUserType();
        this.telephone = user.getTelephone();
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
