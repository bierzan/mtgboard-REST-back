package com.brzn.mtgboard.user;

import com.brzn.mtgboard.exceptionHandler.SQLRecordNotUniqueException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

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
        if (ifUsernameExists(user.getUsername())) {
            throw new SQLRecordNotUniqueException("użytkownik o takiej nazwie już jest zarejestrowany");
        } else if (ifUserEmailExists(user.getEmail())) {
            throw new SQLRecordNotUniqueException("taki e-mail już jest zarejestrowany");
        }
        user.setRegistered(LocalDateTime.now());
        user.setLogged(LocalDateTime.now());
        user.setEnabled(true);
        user.setRole(Role.USER);

        userRepo.save(getUserWithHashedPassword(user));
    }

    public UserToken generateUserToken(UserDTO userFromClient) throws HttpClientErrorException {
        User userFromDB = userRepo.findByUsername(userFromClient.getUsername()); //todo dopisac user not found

        if (userFromDB == null) {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "Niewłaściwa nazwa użytkownika lub hasło");
        }
        logUser(userFromClient, userFromDB);
        return new UserToken(String.valueOf(userFromDB.getId()), getToken(userFromDB));

    }

    private void logUser(UserDTO user, User userFromDB) {
        if (BCrypt.checkpw(user.getPassword(), userFromDB.getPassword())) {
            userFromDB.setLogged(LocalDateTime.now());
            String a = String.valueOf(userFromDB.getId());
            String b = String.valueOf(userFromDB.getLogged());
            String hash = String.valueOf(a.concat(b).hashCode());
            userFromDB.setHalfToken(hash);
            userRepo.updateUserLoggedDateAndHalfTokenById(userFromDB.getLogged().toString(), hash, userFromDB.getId());
        } else {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "Niewłaściwa nazwa użytkownika lub hasło");
        }
    }

    private String getToken(User user) {

        String hash = user.getHalfToken();
        return BCrypt.hashpw(hash, BCrypt.gensalt());
    }


    private User getUserWithHashedPassword(User user) {

        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        return user;
    }

    private boolean ifUsernameExists(String username) {
        return userRepo.findByUsername(username) != null;
    }

    private boolean ifUserEmailExists(String email) {
        return userRepo.findByEmail(email) != null;
    }
}