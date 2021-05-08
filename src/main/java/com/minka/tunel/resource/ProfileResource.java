package com.minka.tunel.resource;

import com.minka.tunel.domain.model.AuditModel;

public class ProfileResource extends AuditModel {
    private Long id;
    private String firstName;
    private String lastName;
    private String portfolio;

    public Long getId() {
        return id;
    }

    public ProfileResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public ProfileResource setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public ProfileResource setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPortfolio() {
        return portfolio;
    }

    public ProfileResource setPortfolio(String portfolio) {
        this.portfolio = portfolio;
        return this;
    }

}
