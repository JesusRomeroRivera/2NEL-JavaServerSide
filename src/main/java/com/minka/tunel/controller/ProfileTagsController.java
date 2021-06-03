package com.minka.tunel.controller;

import com.minka.tunel.domain.model.Profile;
import com.minka.tunel.domain.service.ProfileService;
import com.minka.tunel.resource.ProfileResource;
import com.minka.tunel.resource.SaveProfileResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProfileTagsController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProfileService profileService;

    @PostMapping("/profiles/{profileId}/tags/{tagId}")
    public ProfileResource assignPostTag(@PathVariable Long profileId, @PathVariable Long tagId) {
        return convertToResource(profileService.assignProfileTag(profileId, tagId));
    }

    @DeleteMapping("/profiles/{profileId}/tags/{tagId}")
    public ProfileResource unassignPostTag(@PathVariable Long profileId, @PathVariable Long tagId) {
        return convertToResource(profileService.unassignProfileTag(profileId, tagId));
    }

    private Profile convertToEntity(SaveProfileResource resource){
        return mapper.map(resource, Profile.class);
    }

    private ProfileResource convertToResource(Profile entity) {
        return mapper.map(entity, ProfileResource.class);
    }
}
