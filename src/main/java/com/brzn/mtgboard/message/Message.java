package com.brzn.mtgboard.message;

import com.brzn.mtgboard.message.dto.MessageFromForm;
import com.brzn.mtgboard.offer.Offer;
import com.brzn.mtgboard.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "user_from_id")
    private User userFrom;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "offer_id")
    private Offer offer;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "user_to_id")
    private User userTo;

    @Getter
    @Setter
    private String subject;

    @Getter
    @Setter
    private String message;

    @Getter
    @Setter
    private LocalDateTime date;

}
