package edu.bilkent.bilbilet.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import edu.bilkent.bilbilet.model.Traveler;
import edu.bilkent.bilbilet.model.User;
import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class TravelerRegister {
    @NotNull
    private User user;

    @NotNull
    private Traveler traveler;
}
