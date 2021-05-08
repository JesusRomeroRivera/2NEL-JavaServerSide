package com.minka.tunel.resource;

import com.minka.tunel.domain.model.AuditModel;

public class EnterpriseResource extends AuditModel {
    private Long id;
    private String name;
    private String description;
    private String businessEmail;
    private String corpNumber;

    public Long getId() {
        return id;
    }

    public EnterpriseResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public EnterpriseResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public EnterpriseResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getBusinessEmail() {
        return businessEmail;
    }

    public EnterpriseResource setBusinessEmail(String businessEmail) {
        this.businessEmail = businessEmail;
        return this;
    }

    public String getCorpNumber() {
        return corpNumber;
    }

    public EnterpriseResource setCorpNumber(String corpNumber) {
        this.corpNumber = corpNumber;
        return this;
    }
}
