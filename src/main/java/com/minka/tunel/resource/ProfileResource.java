package com.minka.tunel.resource;

import com.minka.tunel.domain.model.AuditModel;

public class ProfileResource extends AuditModel {
    private Long id;
    private String imageUrl;
    private String firstName;
    private String lastName;
    private String portfolio;
    private String description;
    private String city;

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

    public String getImageUrl() {
        return imageUrl;
    }

    public ProfileResource setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProfileResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getCity() {
        return city;
    }

    public ProfileResource setCity(String city) {
        this.city = city;
        return this;
    }
}
