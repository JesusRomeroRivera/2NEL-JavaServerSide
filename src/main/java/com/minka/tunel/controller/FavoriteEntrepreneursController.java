package com.minka.tunel.controller;

import com.minka.tunel.domain.model.Entrepreneur;
import com.minka.tunel.domain.model.Profile;
import com.minka.tunel.domain.service.EntrepreneurService;
import com.minka.tunel.domain.service.ProfileService;
import com.minka.tunel.resource.EntrepreneurResource;
import com.minka.tunel.resource.ProfileResource;
import com.minka.tunel.resource.SaveEntrepreneurResource;
import com.minka.tunel.resource.SaveProfileResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FavoriteEntrepreneursController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProfileService profileService;

    @Operation(summary = "Assign a Favorite Entrepreneur to a Profile", description = "Assign a Favorite Entrepreneur to a Profile", tags = {"favorite-entrepreneurs"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Favorite Entrepreneur Assigned", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/profiles/{userId}/favoriteEntrepreneurs/{favoriteId}")
    public ProfileResource assignProfileTag(@PathVariable Long userId, @PathVariable Long favoriteId) {
        return convertToResource(profileService.assignFavoriteEntrepreneur(userId, favoriteId));
    }

    @Operation(summary = "Unassign a Favorite Entrepreneur to a Profile", description = "Unassign a Favorite Entrepreneur to a Profile", tags = {"favorite-entrepreneurs"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Favorite Entrepreneur Unassigned", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/profiles/{userId}/favoriteEntrepreneurs/{favoriteId}")
    public ProfileResource unassignProfileTag(@PathVariable Long userId, @PathVariable Long favoriteId) {
        return convertToResource(profileService.assignFavoriteEntrepreneur(userId, favoriteId));
    }

    private Profile convertToEntity(SaveProfileResource resource){
        return mapper.map(resource, Profile.class);
    }

    private ProfileResource convertToResource(Profile entity) {
        return mapper.map(entity, ProfileResource.class);
    }
}
