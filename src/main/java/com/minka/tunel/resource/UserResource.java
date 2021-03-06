package com.minka.tunel.resource;

import com.minka.tunel.domain.model.AuditModel;

public class UserResource extends AuditModel {
    private Long id;
    private String email;
    private String password;
    private int creditCardId;

    public Long getId() {
        return id;
    }

    public UserResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserResource setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserResource setPassword(String password) {
        this.password = password;
        return this;
    }

    public int getCreditCardId() {
        return creditCardId;
    }

    public UserResource setCreditCardId(int creditCardId) {
        this.creditCardId = creditCardId;
        return this;
    }

}
