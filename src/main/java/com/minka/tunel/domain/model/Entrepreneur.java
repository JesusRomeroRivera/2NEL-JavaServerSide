package com.minka.tunel.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@DiscriminatorValue("entrepreneur")
public class Entrepreneur extends Profile {

    @OneToOne(mappedBy = "entrepreneur", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @JsonIgnore
    private Enterprise enterprise;

    public Entrepreneur(){
    }

    public Entrepreneur(@NotNull @Size(max = 20) String firstName, @NotNull @Size(max = 20) String lastName, @NotNull @Size(max = 100) String portfolio, Enterprise enterprise) {
        super(firstName, lastName, portfolio);
        this.enterprise = enterprise;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public Entrepreneur setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
        return this;
    }

    public boolean isAlreadyFavorite(Entrepreneur entrepreneur) {
        return this.getFavoriteEntrepreneurs().contains(entrepreneur);
    }

    public Entrepreneur addFavorite(Entrepreneur entrepreneur) {
        if(!this.isAlreadyFavorite(entrepreneur))
            this.getFavoriteEntrepreneurs().add(entrepreneur);
        return this;
    }

    public Entrepreneur removeFavorite(Entrepreneur entrepreneur) {
        if(this.isAlreadyFavorite(entrepreneur))
            this.getFavoriteEntrepreneurs().remove(entrepreneur);
        return this;
    }
}
