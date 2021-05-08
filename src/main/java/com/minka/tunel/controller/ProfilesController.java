package com.minka.tunel.controller;

import com.minka.tunel.domain.model.Entrepreneur;
import com.minka.tunel.domain.model.Profile;
import com.minka.tunel.domain.service.ProfileService;
import com.minka.tunel.resource.EntrepreneurResource;
import com.minka.tunel.resource.ProfileResource;
import com.minka.tunel.resource.SaveEntrepreneurResource;
import com.minka.tunel.resource.SaveProfileResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProfilesController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ProfileService profileService;

    @GetMapping("/profiles")
    public Page<ProfileResource> getAllProfiles(Pageable pageable) {
        List<ProfileResource> profiles = profileService.getAllProfiles(pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        int profilesCount = profiles.size();
        return new PageImpl<>(profiles, pageable, profilesCount);
    }

    @GetMapping("/profiles/{userId}")
    public ProfileResource getProfileById(
            @PathVariable Long userId) {
        return convertToResource(profileService.getProfileById(userId));
    }

    @PostMapping("/profiles")
    public ProfileResource createProfile(
            @Valid @RequestBody SaveProfileResource resource){
        return convertToResource(profileService.createProfile(convertToEntity(resource)));
    }

    @PutMapping("/profiles/{userId}")
    public ProfileResource updateProfile(
            @PathVariable Long userId,
            @Valid @RequestBody SaveProfileResource resource) {
        return convertToResource(profileService.updateProfile(userId, convertToEntity(resource)));
    }

    private Profile convertToEntity(SaveProfileResource resource) {
        return mapper.map(resource, Profile.class);
    }

    private ProfileResource convertToResource(Profile entity) {
        return mapper.map(entity, ProfileResource.class);
    }
}
