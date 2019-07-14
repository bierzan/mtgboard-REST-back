package com.brzn.mtgboard.user;

import com.brzn.mtgboard.message.Message;
import com.brzn.mtgboard.offer.Offer;
import com.brzn.mtgboard.user.role.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    @Column(unique = true)
    private String username;

    @Email
    private String email;

    @Size(min = 8)
    private String password;

    private boolean enabled;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    private LocalDateTime registered;
    private LocalDateTime logged;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Offer> offers = new ArrayList<>();

    @OneToMany(mappedBy = "userFrom", fetch = FetchType.LAZY)
    private List<Message> messages = new ArrayList<>();

    private String halfToken;

    public User() {
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public void addWantedCard(Offer offer){
        this.offers.add(offer);
    }


}
