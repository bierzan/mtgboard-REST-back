package com.brzn.mtgboard.user;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

@Service
public class SecuredUserService {

    private UserRepo userRepo;

    @Autowired
    public SecuredUserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    boolean checkUserToken (ServletRequest request){
        HttpServletRequest req = (HttpServletRequest) request;
        String id = req.getHeader("authId");
        String token = req.getHeader("authToken");
        String halfToken = userRepo.getOne(Long.valueOf(id)).getHalfToken();
        return BCrypt.checkpw(halfToken,token);
    }
}
