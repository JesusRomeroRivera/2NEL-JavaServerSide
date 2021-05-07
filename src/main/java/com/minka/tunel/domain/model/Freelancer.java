package com.minka.tunel.domain.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@DiscriminatorValue("freelancer")
public class Freelancer extends Profile {
    @NotNull
    private String Specialty;

    public Freelancer(){
    }

    public Freelancer(@NotNull @Size(max = 20) String firstName, @NotNull @Size(max = 20) String lastName, @NotNull @Size(max = 100) String portfolio, @NotNull String specialty) {
        super(firstName, lastName, portfolio);
        Specialty = specialty;
    }

    public String getSpecialty() {
        return Specialty;
    }

    public Freelancer setSpecialty(String specialty) {
        Specialty = specialty;
        return this;
    }
}
