package com.minka.tunel.domain.model;

import java.util.List;

public class Profile extends AuditModel {
    private User User;
    private EMembershipType MembershipType;
    private String FirstName;
    private String LastName;
    private String Portfolio;
    private List<Profile> ProfileTags;
    private List<Request> Requests;
}
