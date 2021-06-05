package com.minka.tunel.resource;

import org.hibernate.annotations.NaturalId;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveProfileResource {

    @NaturalId
    private String firstName;

    @NotNull
    @Size(max = 20)
    @NaturalId
    private String lastName;

    @NotNull
    @Size(max = 100)
    @NaturalId
    private String portfolio;

    public String getFirstName() {
        return firstName;
    }

    public SaveProfileResource setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public SaveProfileResource setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPortfolio() {
        return portfolio;
    }

    public SaveProfileResource setPortfolio(String portfolio) {
        this.portfolio = portfolio;
        return this;
    }
}
