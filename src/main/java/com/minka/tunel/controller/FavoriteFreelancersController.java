package com.minka.tunel.controller;

import com.minka.tunel.domain.model.Freelancer;
import com.minka.tunel.domain.model.Profile;
import com.minka.tunel.domain.service.FreelancerService;
import com.minka.tunel.domain.service.ProfileService;
import com.minka.tunel.resource.*;
import com.minka.tunel.resource.FreelancerResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FavoriteFreelancersController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProfileService profileService;

    @Operation(summary = "Assign a Favorite Freelancer to a Profile", description = "Assign a Favorite Freelancer to a Profile", tags = {"favorite-freelancers"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Favorite Freelancer Assigned", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/profiles/{userId}/favoriteFreelancers/{favoriteId}")
    public ProfileResource assignProfileTag(@PathVariable Long userId, @PathVariable Long favoriteId) {
        return convertToResource(profileService.assignFavoriteFreelancer(userId, favoriteId));
    }

    @Operation(summary = "Unassign a Favorite Freelancer to a Profile", description = "Unassign a Favorite Freelancer to a Profile", tags = {"favorite-freelancers"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Favorite Freelancer Unassigned", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/profiles/{userId}/favoriteFreelancers/{favoriteId}")
    public ProfileResource unassignProfileTag(@PathVariable Long userId, @PathVariable Long favoriteId) {
        return convertToResource(profileService.assignFavoriteFreelancer(userId, favoriteId));
    }

    private Profile convertToEntity(SaveProfileResource resource){
        return mapper.map(resource, Profile.class);
    }

    private ProfileResource convertToResource(Profile entity) {
        return mapper.map(entity, FreelancerResource.class);
    }
}
