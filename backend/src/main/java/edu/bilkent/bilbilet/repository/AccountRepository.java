package edu.bilkent.bilbilet.repository;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import edu.bilkent.bilbilet.model.User;

@Qualifier("example_repo")
@Repository
public class AccountRepository {

    static User[] users = {new User(UUID.randomUUID(), "elo", "elogus", "elo@elo.com", "123456")};
    public User findUserByMail(String mail) {
        return new User(UUID.randomUUID(), "elo", "elogus", "elo@elo.com", "123456");
    }
}
