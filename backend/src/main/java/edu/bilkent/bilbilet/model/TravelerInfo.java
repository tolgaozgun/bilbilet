package edu.bilkent.bilbilet.model;

import edu.bilkent.bilbilet.enums.UserType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TravelerInfo {

    @NotNull
    int userId;

    @NotNull
    User user;

    @NotNull
    UserType userType;

    @NotNull
    Traveler traveler;

}
