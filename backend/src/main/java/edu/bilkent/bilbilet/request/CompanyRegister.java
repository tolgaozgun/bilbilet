package edu.bilkent.bilbilet.request;

import edu.bilkent.bilbilet.model.Company;
import edu.bilkent.bilbilet.model.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyRegister {

    @NotNull
    private User user;

    @NotNull
    private Company company;
}
