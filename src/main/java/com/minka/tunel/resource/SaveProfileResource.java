package com.minka.tunel.resource;

import org.hibernate.annotations.NaturalId;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveProfileResource {

    private String imageUrl;

    @NotNull
    private String firstName;

    @NotNull
    @Size(max = 20)
    private String lastName;

    @NotNull
    private String description;

    @NotNull
    private String city;

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

    public String getImageUrl() {
        return imageUrl;
    }

    public SaveProfileResource setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SaveProfileResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getCity() {
        return city;
    }

    public SaveProfileResource setCity(String city) {
        this.city = city;
        return this;
    }

}
