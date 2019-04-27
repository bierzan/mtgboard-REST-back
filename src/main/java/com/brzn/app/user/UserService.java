package com.brzn.app.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
class UserService {

    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
    }

    public void saveUser(User user) {
        User userWithHashedPassword = getUserWithHashedPassword(user);
        userRepo.save(userWithHashedPassword);
    }

    private User getUserWithHashedPassword(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }
}

//todo sprawdzenie unikalnosc maila i username + wlasny exception
