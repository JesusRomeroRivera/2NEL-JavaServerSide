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

    public Freelancer addFavoriteEntrepreneur(Entrepreneur entrepreneur) {
        if(!this.isAlreadyFavoriteEntrepreneur(entrepreneur))
            this.getFavoriteEntrepreneurs().add(entrepreneur);
        return this;
    }

    public Freelancer addFavoriteFreelancer(Freelancer freelancer) {
        if(!this.isAlreadyFavoriteFreelancer(freelancer))
            this.getFavoriteFreelancers().add(freelancer);
        return this;
    }

    public Freelancer addFavoriteInvestor(Investor investor) {
        if(!this.isAlreadyFavoriteInvestor(investor))
            this.getFavoriteInvestors().add(investor);
        return this;
    }

    public Freelancer removeFavoriteEntrepreneur(Entrepreneur entrepreneur) {
        if(this.isAlreadyFavoriteEntrepreneur(entrepreneur))
            this.getFavoriteEntrepreneurs().remove(entrepreneur);
        return this;
    }

    public Freelancer removeFavoriteFreelancer(Freelancer freelancer) {
        if(this.isAlreadyFavoriteFreelancer(freelancer))
            this.getFavoriteFreelancers().remove(freelancer);
        return this;
    }

    public Freelancer removeFavoriteInvestor(Investor investor) {
        if(this.isAlreadyFavoriteInvestor(investor))
            this.getFavoriteInvestors().remove(investor);
        return this;
    }
}
