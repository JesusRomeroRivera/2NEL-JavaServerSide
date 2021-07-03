package com.minka.tunel.controller;

import com.minka.tunel.domain.model.Investor;
import com.minka.tunel.domain.model.Profile;
import com.minka.tunel.domain.service.InvestorService;
import com.minka.tunel.domain.service.ProfileService;
import com.minka.tunel.resource.*;
import com.minka.tunel.resource.InvestorResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class FavoriteInvestorsController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProfileService profileService;

    @Operation(summary = "Assign a Favorite Investor to a Profile", description = "Assign a Favorite Investor to a Profile", tags = {"favorite-investors"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Favorite Investor Assigned", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/profiles/{userId}/favoriteInvestors/{favoriteId}")
    public ProfileResource assignProfileTag(@PathVariable Long userId, @PathVariable Long favoriteId) {
        return convertToResource(profileService.assignFavoriteInvestor(userId, favoriteId));
    }

    @Operation(summary = "Unassign a Favorite Investor to a Profile", description = "Unassign a Favorite Investor to a Profile", tags = {"favorite-investors"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Favorite Investor Unassigned", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/profiles/{userId}/favoriteInvestors/{favoriteId}")
    public ProfileResource unassignProfileTag(@PathVariable Long userId, @PathVariable Long favoriteId) {
        return convertToResource(profileService.unassignFavoriteInvestor(userId, favoriteId));
    }

    private Profile convertToEntity(SaveProfileResource resource){
        return mapper.map(resource, Profile.class);
    }

    private ProfileResource convertToResource(Profile entity) {
        return mapper.map(entity, ProfileResource.class);
    }
}
