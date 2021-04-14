package com.feng.springit.service;

import com.feng.springit.domain.User;
import com.feng.springit.repository.RoleRepository;
import com.feng.springit.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public  UserService (UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    public User register(User user) {
        // take the password from the from and encoded
        String secret = "{bcrypt}" + bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(secret);

        //confirm password
        user.setConfirmPassword(secret);

        //assign a role to this user
        user.addRole(roleService.findByName("ROLE_USER"));

        //set an activation code

        // disable the user

        //save user
        save(user);

        // send the activation email
        sendActiviationEmail(user);

        //return the user
        return user;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    //@EnableTransactionManagement when app perform multiple db actions such as save, if one save has a problem, the
    //frame work will pause the transaction before commit the action, and throw an error

    @Transactional
    public void saveUser(User... users) {
        //begin transaction
        for (User user: users) {
            logger.info("Saving user: " + user.getEmail());
            userRepository.save(user);
//           use for if we are not using @transactional
//            try {
//                userRepository.save(user);
//            }
//            catch (Exception e) {
//                //rollback transaction - throw exception
//            }
        }

    }

    private void sendActiviationEmail(User user) {
        //... send activation email
    }

}
