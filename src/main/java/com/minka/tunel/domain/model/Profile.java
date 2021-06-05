package com.minka.tunel.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
//@MappedSuperclass
@JsonIgnoreProperties(value = {"id", "user", "eMembershipType", "firstName", "lastName", "portfolio", "profileTags", "requests"}, allowSetters = true, allowGetters = true)

public class Profile extends AuditModel {

    @Id
    @Column(name = "user_id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    //@NotNull
    private EMembershipType eMembershipType;

    private String firstName;

    @NotNull
    @Size(max = 20)
    private String lastName;

    @NotNull
    @Size(max = 100)
    private String portfolio;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "profile_tags",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> profileTags;

    @OneToMany(mappedBy = "profile", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
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

    public List<Tag> getProfileTags() {
        return profileTags;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public Profile setUser(User user) {
        this.user = user;
        return this;
    }

    public Profile setProfileTags(List<Tag> profileTags) {
        this.profileTags = profileTags;
        return this;
    }

    public Profile setRequests(List<Request> requests) {
        this.requests = requests;
        return this;
    }

    public boolean isTaggedWith(Tag tag) {
        return this.getProfileTags().contains(tag);
    }

    public Profile tagWith(Tag tag) {
        if(!this.isTaggedWith(tag))
            this.getProfileTags().add(tag);
        return this;
    }

    public Profile unTagWith(Tag tag) {
        if(this.isTaggedWith(tag))
            this.getProfileTags().remove(tag);
        return this;
    }
}