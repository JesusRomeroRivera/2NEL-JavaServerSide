package com.minka.tunel.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Profile profile;

    private String email;

    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private CreditCard creditCard;

    public User() {

    }

    public User(Profile profile, String email, String password, int creditCardId, CreditCard creditCard) {
        this.profile = profile;
        this.email = email;
        this.password = password;
        this.creditCard = creditCard;
    }

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public Profile getProfile() {
        return profile;
    }

    public User setProfile(Profile profile) {
        this.profile = profile;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public User setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
        return this;
    }



}
