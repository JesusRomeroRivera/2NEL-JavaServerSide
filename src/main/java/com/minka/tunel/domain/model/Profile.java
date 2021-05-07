package com.minka.tunel.domain.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "profiles")
public class Profile extends AuditModel {
    @NotNull
    @Size(max = 100)
    @NaturalId
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "tags")
    private List<Profile> profiles;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "tags")
    private List<Startup> startups;

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


    public List<Profile> getProfiles() {
        return profiles;
    }

    public List<Startup> getStartups() {
        return startups;
    }

}
    private User User;
    private EMembershipType MembershipType;
    private String FirstName;
    private String LastName;
    private String Portfolio;
    private List<Profile> ProfileTags;
    private List<Request> Requests;