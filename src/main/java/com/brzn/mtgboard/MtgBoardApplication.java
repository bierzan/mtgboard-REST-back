package com.brzn.mtgboard;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MtgBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(MtgBoardApplication.class, args);
    }

}
