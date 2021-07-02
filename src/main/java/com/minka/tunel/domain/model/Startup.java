package com.minka.tunel.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private Enterprise enterprise;


    @NotNull
    @Size(max = 100)
    private String name;

    @NotNull
    @Size(max = 200)
    private String description;

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Startup setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public List<Tag> getStartupTags() {
        return startupTags;
    }

    private String imageUrl;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "startup_tags",
            joinColumns = @JoinColumn(name = "startup_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> startupTags;

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


    public Startup setEnterprise(Enterprise enterprise) {
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


    public Startup setStartupTags(List<Tag> startupTags) {
        this.startupTags = startupTags;
        return this;
    }


}
