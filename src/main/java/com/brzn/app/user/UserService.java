package com.brzn.app.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    private UserDao userDao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
    }

    public void saveUser(User user) {
        User userWithHashedPassword = getUserWithHashedPassword(user);
        userDao.save(userWithHashedPassword);
//        return userWithHashedPassword;
    }

    private User getUserWithHashedPassword(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }
}
