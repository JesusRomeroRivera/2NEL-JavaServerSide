package com.minka.tunel.domain.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@DiscriminatorValue("freelancer")
public class Freelancer extends Profile {
    @NotNull
    private String specialty;

    public Freelancer(){
    }

    public Freelancer(@NotNull @Size(max = 20) String firstName, @NotNull @Size(max = 20) String lastName, @NotNull @Size(max = 100) String portfolio, @NotNull String specialty) {
        super(firstName, lastName, portfolio);
        this.specialty = specialty;
    }

    public String getSpecialty() {
        return specialty;
    }

    public Freelancer setSpecialty(String specialty) {
        this.specialty = specialty;
        return this;
    }

    public boolean isAlreadyFavorite(Freelancer freelancer) {
        return this.getFavoriteFreelancers().contains(freelancer);
    }

    public Freelancer addFavorite(Freelancer freelancer) {
        if(!this.isAlreadyFavorite(freelancer))
            this.getFavoriteFreelancers().add(freelancer);
        return this;
    }

    public Freelancer removeFavorite(Freelancer freelancer) {
        if(this.isAlreadyFavorite(freelancer))
            this.getFavoriteFreelancers().remove(freelancer);
        return this;
    }
}
