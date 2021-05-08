package com.minka.tunel.resource;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveStartupResource {

    @NotNull
    @Size(max = 20)
    private String name;

    @NotNull
    @Size(max = 350)
    private String description;

    public String getName() {
        return name;
    }

    public SaveStartupResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SaveStartupResource setDescription(String description) {
        this.description = description;
        return this;
    }
}
