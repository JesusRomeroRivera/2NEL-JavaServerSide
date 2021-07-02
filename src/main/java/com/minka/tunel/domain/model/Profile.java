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
@JsonIgnoreProperties(value = {"id", "imageUrl", "user", "eMembershipType", "firstName", "lastName", "portfolio", "description", "city", "profileTags", "requests", "favoriteEntrepreneurs", "favoriteFreelancers", "favoriteInvestors", "favoriteStartups"}, allowSetters = true, allowGetters = true)

public class Profile extends AuditModel {

    @Id
    @Column(name = "user_id")
    private Long id;

    private String imageUrl;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    //@NotNull
    private EMembershipType eMembershipType;

    @NotNull
    @Size(max = 20)
    private String firstName;

    @NotNull
    @Size(max = 20)
    private String lastName;

    @NotNull
    @Size(max = 300)
    private String description;

    @NotNull
    @Size(max = 100)
    private String city;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "profile_tags",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    @JsonIgnore
    private List<Tag> profileTags;

    @OneToMany(mappedBy = "profile", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private List<Request> requests;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "favorite_entrepreneurs",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "favorite_id")
    )
    @JsonIgnore
    private List<Entrepreneur> favoriteEntrepreneurs;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "favorite_freelancers",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "favorite_id")
    )
    @JsonIgnore
    private List<Freelancer> favoriteFreelancers;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "favorite_investors",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "favorite_id")
    )
    @JsonIgnore
    private List<Investor> favoriteInvestors;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "favorite_startups",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "startup_id")
    )
    @JsonIgnore
    private List<Startup> favoriteStartups;

    public Profile() {
    }

    public Profile(@NotNull @Size(max = 20) String firstName, @NotNull @Size(max = 20) String lastName, @NotNull @Size(max = 100) String portfolio) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public Profile setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Profile seteMembershipType(EMembershipType eMembershipType) {
        this.eMembershipType = eMembershipType;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Profile setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Profile setCity(String city) {
        this.city = city;
        return this;
    }

    public List<Entrepreneur> getFavoriteEntrepreneurs() {
        return favoriteEntrepreneurs;
    }

    public Profile setFavoriteEntrepreneurs(List<Entrepreneur> favoriteEntrepreneurs) {
        this.favoriteEntrepreneurs = favoriteEntrepreneurs;
        return this;
    }

    public List<Freelancer> getFavoriteFreelancers() {
        return favoriteFreelancers;
    }

    public Profile setFavoriteFreelancers(List<Freelancer> favoriteFreelancers) {
        this.favoriteFreelancers = favoriteFreelancers;
        return this;
    }

    public List<Investor> getFavoriteInvestors() {
        return favoriteInvestors;
    }

    public Profile setFavoriteInvestors(List<Investor> favoriteInvestors) {
        this.favoriteInvestors = favoriteInvestors;
        return this;
    }

    public List<Startup> getFavoriteStartups() {
        return favoriteStartups;
    }

    public Profile setFavoriteStartups(List<Startup> favoriteStartups) {
        this.favoriteStartups = favoriteStartups;
        return this;
    }
}