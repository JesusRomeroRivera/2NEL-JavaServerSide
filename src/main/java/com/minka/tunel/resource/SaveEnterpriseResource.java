package com.minka.tunel.resource;

import org.hibernate.annotations.NaturalId;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveEnterpriseResource {
    @NotNull
    @Size(max = 100)
    private String name;

    @NotNull
    @Size(max = 300)
    private String description;

    @NotNull
    @Size(max = 50)
    private String businessEmail;

    @NotNull
    @Size(max = 10)
    private String corpNumber;

    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public SaveEnterpriseResource setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

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
