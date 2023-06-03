package edu.bilkent.bilbilet.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import edu.bilkent.bilbilet.model.*;
import edu.bilkent.bilbilet.request.CompanyRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import edu.bilkent.bilbilet.enums.UserType;
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
                throw new Exception("user is not found");
            }

            String hashedPassword = dbUser.getPassword();
            // String hashedPassword =
            // accountRepository.getPasswordIfUserExist(Long.parseLong(user.getBilkentId()));
            boolean passwordMatch = bCryptPasswordEncoder.matches(user.getPassword(), hashedPassword); /// change
                                                                                                       /// thisssss!!!
            // boolean passwordMatch = hashedPassword.equals(dbUser.getPassword());

            if (!passwordMatch) {
                throw new Exception("passwords do not match");
            }

            System.out.println("passwords are matched");

            // authManager.authenticate(new
            // UsernamePasswordAuthenticationToken(user.getBilkentId(),
            // user.getPassword()));
            // User appUser =
            // accountRepository.findUserByBilkentId(Long.parseLong(user.getBilkentId()));
            final UserDetails userDetails = jwtUserService.loadUserByUsername(user.getEmail());
            final String accessToken = jwtUtils.createAccessToken(userDetails);
            final String refreshToken = jwtUtils.createRefreshToken(userDetails);
            return new RUserToken(dbUser, accessToken, refreshToken);
        } catch (Exception e) {
            System.out.println("login exception");
            e.printStackTrace();
            throw e;
        }
    }

    public RRefreshToken refreshToken(String auth) throws Exception {
        try {
            String username = jwtUtils.extractRefreshUsername(JWTFilter.getTokenWithoutBearer(auth));

            // authManager.authenticate(new
            // UsernamePasswordAuthenticationToken(user.getBilkentId(),
            // user.getPassword()));

            final UserDetails userDetails = jwtUserService.loadUserByUsername(username);
            final String accessToken = jwtUtils.createAccessToken(userDetails);
            return new RRefreshToken(accessToken);

        } catch (Exception e) {
            System.out.println("refresh token exception");
            e.printStackTrace();
            throw e;
        }
    }

    public CompanyInfo getCompanyInfo(int userId) throws Exception {
        try {
            Optional<User> optionalUser = accountRepository.findUserById(userId);

            if (optionalUser.isEmpty()) {
                throw new Exception("user is not found");
            }

            User user = optionalUser.get();

            Optional<Company> company = accountRepository.findCompanyByUserId(userId);

            if (company.isEmpty()) {
                throw new Exception("No company found");
            }

            CompanyInfo companyInfo = new CompanyInfo();
            companyInfo.setUserId(userId);
            companyInfo.setUser(user);
            companyInfo.setUserType(UserType.COMPANY);
            companyInfo.setCompany(company.get());

            return companyInfo;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    public TravelerInfo getTravelerInfo(int userId) throws Exception {
        try {
            Optional<User> optionalUser = accountRepository.findUserById(userId);

            if (optionalUser.isEmpty()) {
                throw new Exception("user is not found");
            }

            User user = optionalUser.get();

            Optional<Traveler> traveler = accountRepository.findTravelerByUserId(userId);

            if (traveler.isEmpty()) {
                throw new Exception("No traveler found");
            }

            TravelerInfo travelerInfo = new TravelerInfo();
            travelerInfo.setUserId(userId);
            travelerInfo.setUser(user);
            travelerInfo.setUserType(UserType.TRAVELER);
            travelerInfo.setTraveler(traveler.get());

            return travelerInfo;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    // public boolean changePassword(String auth, ChangePassword passwords) throws
    // Exception {
    // try {
    // String username =
    // jwtUtils.extractAccessUsername(JWTFilter.getTokenWithoutBearer(auth));
    // User dbUser =
    // accountRepository.findUserByBilkentId(Long.parseLong(username));

    // if (dbUser == null) {
    // throw new Exception("user is not found");
    // }

    // String hashedPassword = dbUser.getPassword();
    // // username is bilkentId in this context

    // // String hashedPassword =
    // accountRepository.getPasswordIfUserExist(Long.parseLong(username));
    // boolean passwordMatch =
    // bCryptPasswordEncoder.matches(passwords.getOldPassword(), hashedPassword);

    // if (!passwordMatch) {
    // System.out.println("passwords does not match");
    // throw new Exception("pasword no match");
    // }

    // System.out.println("passwords are matched");
    // // hash new password
    // String hashedNewPassword =
    // bCryptPasswordEncoder.encode(passwords.getNewPassword());

    // dbUser.setPassword(hashedNewPassword);
    // int result = accountRepository.updatePassword(hashedNewPassword,
    // dbUser.getId());
    // // accountRepository.deleteById(dbUser.getId());
    // // accountRepository.save(dbUser);
    // System.out.println("result: " + result);
    // return true;

    // } catch (Exception e) {
    // System.out.println("password change exception exception");
    // e.printStackTrace();
    // throw e;
    // }
    // }

    public User addUser(User user) throws Exception {
        try {
            System.out.println("user that will be saved: " + user);
            boolean userExist = accountRepository.existsByEmail(user.getEmail());

            if (userExist) {
                throw new Exception("user already exists");
            } else {
                // generate uuid and hash password if user does not exist in the system
                // user.setId(UUID.randomUUID());
                user.setPassword(encodePassword(user.getPassword()));

                // System.out.println("user_id: " + user.getId());
                User newUser = accountRepository.save(user);

                // System.out.println("user_id after register: " +
                // accountRepository.findUserByEmail(user.getEmail()).get().getId());
                return newUser;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public CompanyRegister addCompany(CompanyRegister companyRegister) throws Exception {
        try {
            // Check if user already exists by email
            if (accountRepository.existsByEmail(companyRegister.getUser().getEmail())) {
                throw new Exception("User already exists");
            }

            // Check if traveler already exists by user_id
            if (accountRepository.findCompanyByUserId(companyRegister.getUser().getUserId()).isPresent()) {
                throw new Exception("Company already exists");
            }

            // Add user
            User newUser = addUser(companyRegister.getUser());

            Company companyToAdd = companyRegister.getCompany();
            companyToAdd.setUser_id(newUser.getUserId());

            // Add Company
            Optional<Company> optionalCompany = accountRepository.save(companyToAdd);
            Company newCompany = optionalCompany.isPresent() ? optionalCompany.get() : null;

            // Return both added user data and added traveler data
            CompanyRegister result = new CompanyRegister(newUser, newCompany);
            return result;
        }
        catch (Exception e) {
            // accountRepository.deleteUserByEmail(companyRegister.getUser().getEmail());
            e.printStackTrace();
            throw e;
        }
    }

    public TravelerRegister addTraveler(TravelerRegister travelerRegister) throws Exception {
        try {
            // Check if user already exists by email
            if (accountRepository.existsByEmail(travelerRegister.getUser().getEmail())) {
                throw new Exception("user already exists");
            }

            // Check if traveler already exists by user_id
            if (accountRepository.findTravelerByUserId(travelerRegister.getUser().getUserId()).isPresent()) {
                throw new Exception("traveler already exists");
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
        }
        catch (Exception e) {
            // accountRepository.deleteUserByEmail(travelerRegister.getUser().getEmail());
            e.printStackTrace();
            throw e;
        }
    }

    // @Override
    // public RRegisterStaff addStaff(RegisterStaff staff) throws Exception {
    // try {
    // String generatedPassword = StudentPlacementService.generatePsw();

    // User newUser = addUser(new User(UUID.randomUUID(), staff.getName(),
    // staff.getSurname(), staff.getEmail(), staff.getBilkentId(),
    // generatedPassword, staff.getUserType()));

    // // check if staff has necessary info
    // if (newUser.getUserType() == UserType.COORDINATOR || newUser.getUserType() ==
    // UserType.INSTRUCTOR) {
    // if (staff.getDepartment() == null) {
    // throw new Exception(newUser.getUserType() + " should have a department");
    // }

    // if (staff.getFaculty() == null) {
    // throw new Exception(newUser.getUserType() + " should have a faculty");
    // }
    // } else if (newUser.getUserType() == UserType.FAC_MEMBER && staff.getFaculty()
    // == null) {
    // throw new Exception(newUser.getUserType() + " should have a faculty");
    // }

    // // save staff to database
    // Staff staffDb = Staff.toStaff(new StaffRequest(newUser.getId(),
    // staff.getDepartment(), staff.getFaculty()), newUser);
    // Staff savedStaff = staffRepository.save(staffDb);
    // RInstructorCourseAdd responseInstructorSavedCourses = null;

    // // if staff is user, add instructor course
    // if (newUser.getUserType() == UserType.INSTRUCTOR) {
    // if (staff.getBilkentCourseCodes() != null) {
    // responseInstructorSavedCourses =
    // instructorCourseService.addCourseToInstructor(new
    // InstructorCourseAdd(newUser.getId(), staff.getBilkentCourseCodes()));
    // } else {
    // System.out.println("instructor has no course");
    // }
    // }

    // // set unhashed password to user so that frontend can show it
    // savedStaff.getUser().setPassword(generatedPassword);
    // return new RRegisterStaff(savedStaff, responseInstructorSavedCourses);
    // } catch (Exception e) {
    // throw e;
    // }
    // }

    // @Override
    // public User addUserWithUserRequest(UserRequest userRequest) throws Exception
    // {
    // try {
    // // Convert the User request to User
    // User user = User.toUser(userRequest, null);
    // UUID id = UUID.randomUUID();
    // user.setId(id);

    // // Generate a random password
    // String password = new Random().ints(10, 33, 122).collect(StringBuilder::new,
    // StringBuilder::appendCodePoint, StringBuilder::append)
    // .toString();
    // user.setPassword(encodePassword(password));
    // System.out.println("In account 5");
    // accountRepository.save(user);
    // System.out.println("In account 6");
    // return user;
    // } catch (Exception e) {
    // throw e;
    // }

    // }

    // @Override
    // public HashSet<User> addUserChunk(User[] users) throws Exception {
    // HashSet<User> usersSet = new HashSet<>(Arrays.asList(users));
    // HashSet<User> removedUsers = new HashSet<>();

    // for (User user : usersSet) {
    // try {
    // boolean userExist = accountRepository.existsByBilkentId(user.getBilkentId());

    // if (userExist) {
    // usersSet.remove(user);
    // removedUsers.add(user);
    // } else {
    // user.setId(UUID.randomUUID());
    // // user.setPassword(user.getPassword());
    // user.setPassword(encodePassword(user.getPassword()));
    // }
    // } catch (Exception e) {
    // throw e;
    // }
    // }

    // try {
    // accountRepository.saveAll(usersSet);
    // } catch (Exception e) {
    // System.out.println("users not saved: " + removedUsers);
    // throw e;
    // }

    // return usersSet;
    // }

    // @Override
    // public List<User> getUsers() {
    // try {
    // return accountRepository.findAll();
    // } catch (Exception e) {
    // throw e;
    // }
    // }

    private String encodePassword(String plainPassword) {
        try {
            return bCryptPasswordEncoder.encode(plainPassword);
        } catch (Exception e) {
            throw e;
        }
    }
}