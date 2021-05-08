package com.minka.tunel.resource;

import org.hibernate.annotations.NaturalId;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveCreditCardResource {
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

    public String getCardNumber() {
        return cardNumber;
    }

    public SaveCreditCardResource setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public String getCvv() {
        return cvv;
    }

    public SaveCreditCardResource setCvv(String cvv) {
        this.cvv = cvv;
        return this;
    }

    public String getExpMonth() {
        return expMonth;
    }

    public SaveCreditCardResource setExpMonth(String expMonth) {
        this.expMonth = expMonth;
        return this;
    }

    public String getExpYear() {
        return expYear;
    }

    public SaveCreditCardResource setExpYear(String expYear) {
        this.expYear = expYear;
        return this;
    }

}
