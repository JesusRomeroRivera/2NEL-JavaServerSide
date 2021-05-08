package com.minka.tunel.resource;

import org.hibernate.annotations.NaturalId;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveRequestResource {
    @NotNull
    @Size(max = 100)
    @NaturalId
    private String subject;

    public String getSubject() {
        return subject;
    }

    public SaveRequestResource setSubject(String subject) {
        this.subject = subject;
        return this;
    }
}
