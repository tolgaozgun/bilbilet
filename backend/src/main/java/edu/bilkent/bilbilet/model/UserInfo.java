package edu.bilkent.bilbilet.model;

import edu.bilkent.bilbilet.enums.UserType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    @NotNull
    int userId;

    @NotNull
    User user;

    @NotNull
    UserType userType;

    // This information is either Admin, Company or Traveler
    @NotNull
    Object information;

}
