package com.minka.tunel.domain.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "credit_cards")
public class CreditCard {
    private User user;
    private String cardNumber;
    private String cvv;
    private String expMonth;
    private String expYear;
}
