package com.minka.tunel.domain.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "requests")
public class Request extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Profile profile;

    @NotNull
    @Size(max = 100)
    @NaturalId
    private String subject;

    public Request() {
    }

    public Request(@NotNull @Size(max = 100) String subject) {
        this.subject = subject;
    }

    public Long getId() {
        return id;
    }

    public Request setId(Long id) {
        this.id = id;
        return this;
    }

    public Profile getProfile() {
        return profile;
    }

    public String getSubject() {
        return subject;
    }

    public Request setSubject(String subject) {
        this.subject = subject;
        return this;
    }

}
