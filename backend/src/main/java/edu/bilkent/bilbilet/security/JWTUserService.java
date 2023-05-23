package edu.bilkent.bilbilet.security;

import java.util.Collections;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.bilkent.bilbilet.model.User;
import edu.bilkent.bilbilet.repository.AccountRepository;

import org.springframework.security.core.userdetails.UserDetailsService;


// import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JWTUserService implements UserDetailsService {
    private final AccountRepository accountRepository;

    @Override
    // @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User appUser = accountRepository.findUserByMail(username); // ! here username is mail
    
            UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                                        username, 
                                        appUser.getPassword(), 
                                        Collections.singleton(new SimpleGrantedAuthority(appUser.getUserType().toString()))
                                        // null
                                        // Collections.singleton(new SimpleGrantedAuthority(appUser.getUserType().toString()))
                                        );
            return userDetails; 
        } catch (Exception e) {
            throw e;
        }
    }

    // @Override
    public UserDetails loadUserByUsername(User appUser) throws UsernameNotFoundException {
        try {
        
            UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                                        appUser.getEmail(), 
                                        appUser.getPassword()
                                        // Collections.singleton(new SimpleGrantedAuthority(appUser.getUserType().toString()))
                                        , null
                                        );
            return userDetails; 
        } catch (Exception e) {
            throw e;
        }
    }    
}
