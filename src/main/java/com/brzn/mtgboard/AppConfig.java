package com.brzn.mtgboard;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(value = "com.brzn.mtgboard")
public class AppConfig {

//    @Bean
//    public SimpleMailMessage templateSimpleMessage() {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setText(
//                "Wiadomość od: %s, %s\n" +
//                        "do: %s, %s\n" +
//                        "Dotyczy oferty:\n" +
//                        "%s\n\n" +
//                        "Treść wiadomości:\n" +
//                        "%s\n" +
//                        "\n\n" +
//                        "Wiadomość wygenerowana automatycznie przez serwis MTGBOARD");
//        return message;
//    }
}
