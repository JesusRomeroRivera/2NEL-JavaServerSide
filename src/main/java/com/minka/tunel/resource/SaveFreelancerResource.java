package com.minka.tunel.resource;

import javax.validation.constraints.NotNull;

public class SaveFreelancerResource {
    @NotNull
    private String specialty;

    public String getSpecialty() {
        return specialty;
    }

    public SaveFreelancerResource setSpecialty(String specialty) {
        this.specialty = specialty;
        return this;
    }
}
