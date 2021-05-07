package com.minka.tunel.domain.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "tags")
public class Tag extends AuditModel {
    @Id
    private Long id;

    @NotNull
    @Size(max = 100)
    @NaturalId
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "tags")
    private List<_Profile> profiles;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "tags")
    private List<_Startup> startups;

    public Tag() {
    }

    public Tag(@NotNull @Size(max = 100) String name) {
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public Tag setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Tag setName(String name) {
        this.name = name;
        return this;
    }


    public List<_Profile> getProfiles() {
        return profiles;
    }

    public List<_Startup> getStartups() {
        return startups;
    }

}