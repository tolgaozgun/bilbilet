package edu.bilkent.bilbilet.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.bilkent.bilbilet.exception.AlreadyExistException;
import edu.bilkent.bilbilet.exception.ItemNotFoundException;
import edu.bilkent.bilbilet.exception.PasswordNotMatchException;
import edu.bilkent.bilbilet.model.Traveler;
import edu.bilkent.bilbilet.model.User;
import edu.bilkent.bilbilet.repository.AccountRepository;
import edu.bilkent.bilbilet.request.TravelerRegister;
import edu.bilkent.bilbilet.request.UserLogin;
import edu.bilkent.bilbilet.response.RRefreshToken;
import edu.bilkent.bilbilet.response.RUserToken;
import edu.bilkent.bilbilet.security.JWTFilter;
import edu.bilkent.bilbilet.security.JWTUserService;
import edu.bilkent.bilbilet.security.JWTUtils;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AccountService {
    public static int hashStrength = 10;

    @Autowired
    final BCryptPasswordEncoder bCryptPasswordEncoder;

    // @Autowired
    private final AccountRepository accountRepository;
    private final JWTUserService jwtUserService;

    @Autowired
    private final JWTUtils jwtUtils;

    public RUserToken login(UserLogin user) throws Exception {
        try {
            User dbUser = accountRepository.findUserByMail(user.getEmail());

            if (dbUser == null) {
                throw new ItemNotFoundException("User is not found");
            }
            
            String hashedPassword = dbUser.getPassword();
            //String hashedPassword = accountRepository.getPasswordIfUserExist(Long.parseLong(user.getBilkentId()));
            boolean passwordMatch = bCryptPasswordEncoder.matches(user.getPassword(), hashedPassword); /// change thisssss!!!
            // boolean passwordMatch = hashedPassword.equals(dbUser.getPassword());

            if (!passwordMatch) {
                throw new PasswordNotMatchException("Passwords do not match");
            }

            System.out.println("Passwords are matched");

            // authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getBilkentId(), user.getPassword()));
            // User appUser = accountRepository.findUserByBilkentId(Long.parseLong(user.getBilkentId()));
            final UserDetails userDetails = jwtUserService.loadUserByUsername(user.getEmail());
            final String accessToken = jwtUtils.createAccessToken(userDetails);
            final String refreshToken = jwtUtils.createRefreshToken(userDetails);
            return new RUserToken(dbUser, accessToken, refreshToken);
        } catch (PasswordNotMatchException e) {
            throw e;
        } catch (ItemNotFoundException e) {
            throw e;
        } catch (Exception e) {
            System.out.println("login exception");
            e.printStackTrace();
            throw e;
        }
    }

    public RRefreshToken refreshToken(String auth) throws Exception {
        try {
            String username = jwtUtils.extractRefreshUsername(JWTFilter.getTokenWithoutBearer(auth));

            // authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getBilkentId(), user.getPassword()));

            final UserDetails userDetails = jwtUserService.loadUserByUsername(username);
            final String accessToken = jwtUtils.createAccessToken(userDetails);
            return new RRefreshToken(accessToken);
            
        } catch (Exception e) {
            System.out.println("refresh token exception");
            e.printStackTrace();
            throw e;
        }
    }

    // public boolean changePassword(String auth, ChangePassword passwords) throws Exception {
    //     try {
    //         String username = jwtUtils.extractAccessUsername(JWTFilter.getTokenWithoutBearer(auth));
    //         User dbUser = accountRepository.findUserByBilkentId(Long.parseLong(username));

    //         if (dbUser == null) {
    //             throw new Exception("user is not found");
    //         }
            
    //         String hashedPassword = dbUser.getPassword();
    //         // username is bilkentId in this context
            
            
    //         // String hashedPassword = accountRepository.getPasswordIfUserExist(Long.parseLong(username));
    //         boolean passwordMatch = bCryptPasswordEncoder.matches(passwords.getOldPassword(), hashedPassword);

    //         if (!passwordMatch) {
    //             System.out.println("passwords does not match");
    //             throw new Exception("pasword no match");
    //         }

    //         System.out.println("passwords are matched");
    //         // hash new password
    //         String hashedNewPassword = bCryptPasswordEncoder.encode(passwords.getNewPassword());
            
    //         dbUser.setPassword(hashedNewPassword);
    //         int result = accountRepository.updatePassword(hashedNewPassword, dbUser.getId());
    //         // accountRepository.deleteById(dbUser.getId());
    //         // accountRepository.save(dbUser);
    //         System.out.println("result: " + result);
    //         return true;
            
    //     } catch (Exception e) {
    //         System.out.println("password change exception exception");
    //         e.printStackTrace();
    //         throw e;
    //     }
    // }

    public User addUser(User user) throws Exception {
        try {
            System.out.println("user that will be saved: " + user);
            boolean userExist = accountRepository.existsByEmail(user.getEmail());
            
            if (userExist) {
                throw new AlreadyExistException("User already exists");
            } else {
                // generate uuid and hash password if user does not exist in the system
                // user.setId(UUID.randomUUID());
                user.setPassword(encodePassword(user.getPassword()));

                // System.out.println("user_id: " + user.getId());
                User newUser = accountRepository.save(user);

                // System.out.println("user_id after register: " + accountRepository.findUserByEmail(user.getEmail()).get().getId());
                return newUser;
            }
        } catch (AlreadyExistException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public TravelerRegister addTraveler(TravelerRegister travelerRegister) throws Exception {
        try {
            // Check if user already exists by email
            if (accountRepository.existsByEmail(travelerRegister.getUser().getEmail())) {
                throw new AlreadyExistException("User already exists");
            }
            
            // Check if traveler already exists by user_id
            if (accountRepository.findTravelerByUserId(travelerRegister.getUser().getUserId()).isPresent()) {
                throw new AlreadyExistException("Traveler already exists");
            }
            
            // Add user
            User newUser = addUser(travelerRegister.getUser());
            
            // Add traveler
            Traveler travelerToAdd = travelerRegister.getTraveler();
            travelerToAdd.setUser_id(newUser.getUserId());

            Optional<Traveler> optionalTraveler = accountRepository.save(travelerToAdd);
            Traveler newTraveler = optionalTraveler.isPresent() ? optionalTraveler.get() : null;

            // Return both added user data and added traveler data
            TravelerRegister result = new TravelerRegister(newUser, newTraveler);
            return result;
        } catch (AlreadyExistException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private String encodePassword(String plainPassword) {
        try {
            return bCryptPasswordEncoder.encode(plainPassword);
        } catch (Exception e) {
            throw e;
        }       
    }
}