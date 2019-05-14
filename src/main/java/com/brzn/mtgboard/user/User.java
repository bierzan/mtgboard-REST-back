package com.brzn.mtgboard.user;

import com.brzn.mtgboard.card.wanted.WantedCard;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;

    @NotEmpty
    @Column(unique = true)
    @Getter
    @Setter
    private String username;

    @Email
    @Getter
    @Setter
    private String email;

    @Size(min = 8)
    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private LocalDateTime registered;

    @Getter
    @Setter
    private LocalDateTime logged;

    @Getter
    @Setter
    private boolean enabled;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private Role role;

    @Getter
    @Setter
    @OneToMany(mappedBy = "user")
    private List<WantedCard> wantedCards = new ArrayList<>();

    public User() {
    }

}
