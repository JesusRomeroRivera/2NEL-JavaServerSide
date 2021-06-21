package com.minka.tunel.controller;

import com.minka.tunel.domain.model.Freelancer;
import com.minka.tunel.domain.service.FreelancerService;
import com.minka.tunel.resource.FreelancerResource;
import com.minka.tunel.resource.FreelancerResource;
import com.minka.tunel.resource.SaveFreelancerResource;
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
    private FreelancerService freelancerService;

    @Operation(summary = "Assign a Favorite Freelancer to a Profile", description = "Assign a Favorite Freelancer to a Profile", tags = {"favorite-freelancers"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Favorite Freelancer Assigned", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/profiles/{userId}/favoriteFreelancers/{favoriteId}")
    public FreelancerResource assignProfileTag(@PathVariable Long userId, @PathVariable Long favoriteId) {
        return convertToResource(freelancerService.assignFavoriteFreelancer(userId, favoriteId));
    }

    @Operation(summary = "Unassign a Favorite Freelancer to a Profile", description = "Unassign a Favorite Freelancer to a Profile", tags = {"favorite-freelancers"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Favorite Freelancer Unassigned", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/profiles/{userId}/favoriteFreelancers/{favoriteId}")
    public FreelancerResource unassignProfileTag(@PathVariable Long userId, @PathVariable Long favoriteId) {
        return convertToResource(freelancerService.assignFavoriteFreelancer(userId, favoriteId));
    }

    private Freelancer convertToEntity(SaveFreelancerResource resource){
        return mapper.map(resource, Freelancer.class);
    }

    private FreelancerResource convertToResource(Freelancer entity) {
        return mapper.map(entity, FreelancerResource.class);
    }
}
