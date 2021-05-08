package com.minka.tunel.resource;

import com.minka.tunel.domain.model.AuditModel;

public class RequestResource extends AuditModel {
    private Long id;
    private String subject;

    public Long getId() {
        return id;
    }

    public RequestResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public RequestResource setSubject(String subject) {
        this.subject = subject;
        return this;
    }

}
