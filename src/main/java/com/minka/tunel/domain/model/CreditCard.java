package com.minka.tunel.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "credit_cards")
public class CreditCard extends AuditModel {

    public Long getId() {
        return id;
    }

    public CreditCard setId(Long id) {
        this.id = id;
        return this;
    }

    public CreditCard setUser(User user) {
        this.user = user;
        return this;
    }

    @Id
    @Column(name = "user_id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @NotNull
    @Size(min = 16)
    @Size(max = 16)
    private String cardNumber;

    @NotNull
    @Size(min = 3)
    @Size(max = 4)
    private String cvv;

    @NotNull
    @Size(min=1)
    @Size(max=2)
    private String expMonth;

    @NotNull
    @Size(max = 4)
    private String expYear;

    public CreditCard(){
    }

    public CreditCard(@NotNull @Size(min = 16) @Size(max = 16) String cardNumber, @NotNull @Size(min = 3) @Size(max = 4) String cvv, @NotNull @Size(min = 1) @Size(max = 2) String expMonth, @NotNull @Size(max = 4) String expYear) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expMonth = expMonth;
        this.expYear = expYear;
    }

    public User getUser() {
        return user;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public CreditCard setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public String getCvv() {
        return cvv;
    }

    public CreditCard setCvv(String cvv) {
        this.cvv = cvv;
        return this;
    }

    public String getExpMonth() {
        return expMonth;
    }

    public CreditCard setExpMonth(String expMonth) {
        this.expMonth = expMonth;
        return this;
    }

    public String getExpYear() {
        return expYear;
    }

    public CreditCard setExpYear(String expYear) {
        this.expYear = expYear;
        return this;
    }
}
