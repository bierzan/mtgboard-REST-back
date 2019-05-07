package com.brzn.mtgboard.user;

import com.brzn.mtgboard.exceptionHandler.SQLRecordNotUniqueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
class UserService {

    private UserRepo userRepo;
//    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    public void saveUser(User user) throws SQLRecordNotUniqueException {
        if (isUsernameUnique(user.getUsername())) {
            throw new SQLRecordNotUniqueException("użytkownik o takiej nazwie już jest zarejestrowany");
        } else if (isUserEmailUnique(user.getEmail())) {
            throw new SQLRecordNotUniqueException("taki e-mail już jest zarejestrowany");
        }

        userRepo.save(user);
    }


//    private User getUserWithHashedPassword(User user) {
//
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        return user;
//    }

    private boolean isUsernameUnique(String username) {
        if (userRepo.findByUsername(username) == null) {
            return false;
        }
        return true;
    }

    private boolean isUserEmailUnique(String email) {
        if (userRepo.findByEmail(email) == null) {
            return false;
        }
        return true;
    }
}