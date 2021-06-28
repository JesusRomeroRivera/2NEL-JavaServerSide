package com.minka.tunel.controller;

import com.minka.tunel.domain.model.Profile;
import com.minka.tunel.domain.service.ProfileService;
import com.minka.tunel.resource.ProfileResource;
import com.minka.tunel.resource.SaveProfileResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProfileTagsController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProfileService profileService;

    @Operation(summary = "Assign a Tag to a Profile", description = "Assign a Tag to a Profile", tags = {"profile-tags"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tag Assigned", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/profiles/{profileId}/tags/{tagId}")
    public ProfileResource assignProfileTag(@PathVariable Long profileId, @PathVariable Long tagId) {
        return convertToResource(profileService.assignProfileTag(profileId, tagId));
    }

    @Operation(summary = "Unassign a Tag to a Profile", description = "Unassign a Tag to a Profile", tags = {"profile-tags"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tag Unassigned", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/profiles/{profileId}/tags/{tagId}")
    public ProfileResource unassignProfileTag(@PathVariable Long profileId, @PathVariable Long tagId) {
        return convertToResource(profileService.unassignProfileTag(profileId, tagId));
    }

    private Profile convertToEntity(SaveProfileResource resource){
        return mapper.map(resource, Profile.class);
    }

    private ProfileResource convertToResource(Profile entity) {
        return mapper.map(entity, ProfileResource.class);
    }
}
