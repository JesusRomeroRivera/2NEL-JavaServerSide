package com.minka.tunel.domain.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "enterprises")
public class Enterprise extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Entrepreneur entrepreneur;

    @NotNull
    @Size(max = 100)
    @NaturalId
    private String name;

    @NotNull
    @Size(max = 300)
    @NaturalId
    private String description;

    @NotNull
    @Size(max = 50)
    @NaturalId
    private String businessEmail;

    @NotNull
    @Size(max = 10)
    @NaturalId
    private String corpNumber;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "enterprises")
    private List<Startup> startups;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "enterprises")
    private List<Request> requests;

    public Enterprise() {

    }

    public Enterprise(@NotNull @Size(max = 100) String name, @NotNull @Size(max = 300) String description, @NotNull @Size(max = 50) String businessEmail, @NotNull @Size(max = 10) String corpNumber) {
        this.name = name;
        this.description = description;
        this.businessEmail = businessEmail;
        this.corpNumber = corpNumber;
    }

    public Long getId() {
        return id;
    }

    public Enterprise setId(Long id) {
        this.id = id;
        return this;
    }

    public Entrepreneur getEntrepreneur() {
        return entrepreneur;
    }

    public String getName() {
        return name;
    }

    public Enterprise setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Enterprise setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getBusinessEmail() {
        return businessEmail;
    }

    public Enterprise setBusinessEmail(String businessEmail) {
        this.businessEmail = businessEmail;
        return this;
    }

    public String getCorpNumber() {
        return corpNumber;
    }

    public Enterprise setCorpNumber(String corpNumber) {
        this.corpNumber = corpNumber;
        return this;
    }

    public List<Startup> getStartups() {
        return startups;
    }

    public List<Request> getRequests() {
        return requests;
    }
}

