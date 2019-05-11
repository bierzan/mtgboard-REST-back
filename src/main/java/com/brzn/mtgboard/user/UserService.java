package com.brzn.mtgboard.user;

import com.brzn.mtgboard.exceptionHandler.SQLRecordNotUniqueException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
class UserService {

    private UserRepo userRepo;

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
        user.setRegistered(LocalDateTime.now());
        user.setLogged(LocalDateTime.now());
        user.setEnabled(true);
        user.setRole(Role.USER);

        userRepo.save(getUserWithHashedPassword(user));
    }


    private User getUserWithHashedPassword(User user) {

        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        return user;
    }

    private boolean isUsernameUnique(String username) {
        return userRepo.findByUsername(username) != null;
    }

    private boolean isUserEmailUnique(String email) {
        return userRepo.findByEmail(email) != null;
    }
}