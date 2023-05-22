package edu.bilkent.bilbilet.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import edu.bilkent.bilbilet.enums.UserType;
import edu.bilkent.bilbilet.model.User;

@Qualifier("example_repo")
@Repository
public class AccountRepository {

    static User[] arr = {new User(0, "elo", "elogus", "elo@elo.com", "555222", "123456", UserType.ADMIN)};
    static List<User> users = Arrays.asList(arr);
    
    public User findUserByMail(String mail) {
        return users.get(0);
    }

    public User save(User user) {
        users.add(user);
        return user;
    }

    public boolean existsByEmail(String mail) {
        return mail.equals(users.get(0).getEmail());
    }
}
