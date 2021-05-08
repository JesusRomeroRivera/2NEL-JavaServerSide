package com.minka.tunel.resource;

import com.minka.tunel.domain.model.Profile;

public class FreelancerResource extends Profile {
    private String specialty;

    public String getSpecialty() {
        return specialty;
    }

    public FreelancerResource setSpecialty(String specialty) {
        this.specialty = specialty;
        return this;
    }

}
