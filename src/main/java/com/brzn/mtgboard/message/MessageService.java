package com.brzn.mtgboard.message;

import com.brzn.mtgboard.message.dto.MessageDataForEmail;
import com.brzn.mtgboard.message.dto.MessageFromForm;
import com.brzn.mtgboard.offer.OfferService;
import com.brzn.mtgboard.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLDataException;
import java.time.LocalDateTime;

@Service
@Transactional
public class MessageService {

    private MessageRepo messageRepo;
    private UserService userService;
    private OfferService offerService;
    private EmailSender emailSender;
    private SimpleMailMessage simpleMailMessage;

    @Autowired
    public MessageService(MessageRepo messageRepo, UserService userService, OfferService offerService, EmailSender emailSender) {
        this.messageRepo = messageRepo;
        this.userService = userService;
        this.offerService = offerService;
        this.emailSender = emailSender;
    }

    public Message send(MessageFromForm msg) throws SQLDataException {

        Message sendMessage = mapFromForm(msg);

        emailSender.sendTemplateMessage(new MessageDataForEmail(sendMessage));

        sendMessage.setDate(LocalDateTime.now());
        messageRepo.save(sendMessage);
        return sendMessage;

    }

    private Message mapFromForm(MessageFromForm msg) throws SQLDataException {
        Message result = new Message();
        result.setOffer(offerService.findOfferById(msg.getOfferId()));
        result.setUserFrom(userService.getUserById(msg.getUserFromId()));
        result.setUserTo(userService.getUserById(msg.getUserToId()));

        result.setMessage(msg.getMessage());
        result.setSubject(msg.getSubject());
        return result;
    }
}