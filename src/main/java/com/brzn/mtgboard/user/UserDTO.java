package com.brzn.mtgboard.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class UserDTO {

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;

    public UserDTO() {
    }

}
