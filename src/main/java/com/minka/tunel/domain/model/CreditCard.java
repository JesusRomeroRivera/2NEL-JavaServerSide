package com.minka.tunel.domain.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "credit_cards")
public class CreditCard extends AuditModel {

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private User user;

    @NotNull
    @Size(min = 16)
    @Size(max = 16)
    @NaturalId
    private String cardNumber;

    @NotNull
    @Size(min = 3)
    @Size(max = 4)
    @NaturalId
    private String cvv;

    @NotNull
    @Size(min=1)
    @Size(max=2)
    @NaturalId
    private String expMonth;

    @NotNull
    @Size(max = 4)
    @NaturalId
    private String expYear;

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
