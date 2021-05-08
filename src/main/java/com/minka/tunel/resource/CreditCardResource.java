package com.minka.tunel.resource;

import com.minka.tunel.domain.model.AuditModel;

public class CreditCardResource extends AuditModel {
    private String cardNumber;
    private String cvv;
    private String expMonth;
    private String expYear;

    public String getCardNumber() {
        return cardNumber;
    }

    public CreditCardResource setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public String getCvv() {
        return cvv;
    }

    public CreditCardResource setCvv(String cvv) {
        this.cvv = cvv;
        return this;
    }

    public String getExpMonth() {
        return expMonth;
    }

    public CreditCardResource setExpMonth(String expMonth) {
        this.expMonth = expMonth;
        return this;
    }

    public String getExpYear() {
        return expYear;
    }

    public CreditCardResource setExpYear(String expYear) {
        this.expYear = expYear;
        return this;
    }

}
