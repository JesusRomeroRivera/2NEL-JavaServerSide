package com.minka.tunel.domain.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "startups")
public class Startup extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private _Enterprise enterprise;


    @NotNull
    @Size(max = 100)
    @NaturalId
    private String name;

    @NotNull
    @Size(max = 200)
    @NaturalId
    private String description;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "startups")
    private List<_StartupTags> startupTags;

    public Startup() {
    }

    public Startup(@NotNull @Size(max = 100) String name, @NotNull @Size(max = 200) String description) {
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Startup setEnterprise(_Enterprise enterprise) {
        this.enterprise = enterprise;
        return this;
    }

    public String getName() {
        return name;
    }

    public Startup setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Startup setDescription(String description) {
        this.description = description;
        return this;
    }


    public Startup setStartupTags(List<_StartupTags> startupTags) {
        this.startupTags = startupTags;
        return this;
    }


}
