package com.minka.tunel.resource;

import com.minka.tunel.domain.model.AuditModel;

public class StartupResource extends AuditModel {
    private Long id;
    private String name;
    private String description;

    public Long getId() {
        return id;
    }

    public StartupResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public StartupResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public StartupResource setDescription(String description) {
        this.description = description;
        return this;
    }

}
