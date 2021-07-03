package com.minka.tunel.domain.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("investor")
public class Investor extends Profile {
    public boolean isAlreadyFavorite(Investor investor) {
        return this.getFavoriteInvestors().contains(investor);
    }

    public Investor addFavoriteEntrepreneur(Entrepreneur entrepreneur) {
        if(!this.isAlreadyFavoriteEntrepreneur(entrepreneur))
            this.getFavoriteEntrepreneurs().add(entrepreneur);
        return this;
    }

    public Investor addFavoriteFreelancer(Freelancer freelancer) {
        if(!this.isAlreadyFavoriteFreelancer(freelancer))
            this.getFavoriteFreelancers().add(freelancer);
        return this;
    }

    public Investor addFavoriteInvestor(Investor investor) {
        if(!this.isAlreadyFavoriteInvestor(investor))
            this.getFavoriteInvestors().add(investor);
        return this;
    }

    public Investor removeFavoriteEntrepreneur(Entrepreneur entrepreneur) {
        if(this.isAlreadyFavoriteEntrepreneur(entrepreneur))
            this.getFavoriteEntrepreneurs().remove(entrepreneur);
        return this;
    }

    public Investor removeFavoriteFreelancer(Freelancer freelancer) {
        if(this.isAlreadyFavoriteFreelancer(freelancer))
            this.getFavoriteFreelancers().remove(freelancer);
        return this;
    }

    public Investor removeFavoriteInvestor(Investor investor) {
        if(this.isAlreadyFavoriteInvestor(investor))
            this.getFavoriteInvestors().remove(investor);
        return this;
    }
}
