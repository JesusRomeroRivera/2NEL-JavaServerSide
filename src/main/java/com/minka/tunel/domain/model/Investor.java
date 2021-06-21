package com.minka.tunel.domain.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("investor")
public class Investor extends Profile {
    public boolean isAlreadyFavorite(Investor investor) {
        return this.getFavoriteInvestors().contains(investor);
    }

    public Investor addFavorite(Investor investor) {
        if(!this.isAlreadyFavorite(investor))
            this.getFavoriteInvestors().add(investor);
        return this;
    }

    public Investor removeFavorite(Investor investor) {
        if(this.isAlreadyFavorite(investor))
            this.getFavoriteInvestors().remove(investor);
        return this;
    }
}
