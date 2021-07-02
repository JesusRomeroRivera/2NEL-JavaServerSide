package com.minka.tunel.resource;

import com.minka.tunel.domain.model.Profile;

public class FreelancerResource extends ProfileResource {
    private String specialty;

    public String getSpecialty() {
        return specialty;
    }

    public FreelancerResource setSpecialty(String specialty) {
        this.specialty = specialty;
        return this;
    }

}
