package com.minka.tunel.resource;

import org.hibernate.annotations.NaturalId;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveEnterpriseResource {
    @NotNull
    @Size(max = 100)
    @NaturalId
    private String name;

    @NotNull
    @Size(max = 300)
    @NaturalId
    private String description;

    @NotNull
    @Size(max = 50)
    @NaturalId
    private String businessEmail;

    @NotNull
    @Size(max = 10)
    @NaturalId
    private String corpNumber;

    public String getName() {
        return name;
    }

    public SaveEnterpriseResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SaveEnterpriseResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getBusinessEmail() {
        return businessEmail;
    }

    public SaveEnterpriseResource setBusinessEmail(String businessEmail) {
        this.businessEmail = businessEmail;
        return this;
    }

    public String getCorpNumber() {
        return corpNumber;
    }

    public SaveEnterpriseResource setCorpNumber(String corpNumber) {
        this.corpNumber = corpNumber;
        return this;
    }

}
