package com.brzn.mtgboard.message.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

public class    MessageFromForm {

    @Getter
    @Setter
    private long userFromId;

    @Getter
    @Setter
    private long userToId;

    @Getter
    @Setter
    private long offerId;

    @Getter
    @Setter
    private String message;

}
