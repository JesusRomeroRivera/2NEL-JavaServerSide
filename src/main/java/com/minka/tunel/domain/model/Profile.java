package com.minka.tunel.domain.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

import static javax.persistence.InheritanceType.SINGLE_TABLE;


@Entity
@Table(name = "profiles")
@Inheritance(strategy=SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING,
        name = "profile_type")
@MappedSuperclass
public class Profile extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id", nullable = false)
    @NaturalId
    private User user;

    @NotNull
    private EMembershipType eMembershipType;

    @NotNull
    @Size(max = 20)
    @NaturalId
    private String firstName;

    @NotNull
    @Size(max = 20)
    @NaturalId
    private String lastName;

    @NotNull
    @Size(max = 100)
    @NaturalId
    private String portfolio;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "profiles")
    private List<Profile> profileTags;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "profiles")
    private List<Request> requests;

    public Profile() {
    }

    public Profile(@NotNull @Size(max = 20) String firstName, @NotNull @Size(max = 20) String lastName, @NotNull @Size(max = 100) String portfolio) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.portfolio = portfolio;
    }

    public Long getId() {
        return id;
    }

    public Profile setId(Long id) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public EMembershipType geteMembershipType() {
        return eMembershipType;
    }

    public String getFirstName() {
        return firstName;
    }

    public Profile setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Profile setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPortfolio() {
        return portfolio;
    }

    public Profile setPortfolio(String portfolio) {
        this.portfolio = portfolio;
        return this;
    }

    public List<Profile> getProfileTags() {
        return profileTags;
    }

    public List<Request> getRequests() {
        return requests;
    }
}