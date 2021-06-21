package com.minka.tunel.controller;

import com.minka.tunel.domain.model.Investor;
import com.minka.tunel.domain.service.InvestorService;
import com.minka.tunel.resource.InvestorResource;
import com.minka.tunel.resource.InvestorResource;
import com.minka.tunel.resource.SaveInvestorResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FavoriteInvestorsController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private InvestorService investorService;

    @Operation(summary = "Assign a Favorite Investor to a Profile", description = "Assign a Favorite Investor to a Profile", tags = {"favorite-investors"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Favorite Investor Assigned", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/profiles/{userId}/favoriteInvestors/{favoriteId}")
    public InvestorResource assignProfileTag(@PathVariable Long userId, @PathVariable Long favoriteId) {
        return convertToResource(investorService.assignFavoriteInvestor(userId, favoriteId));
    }

    @Operation(summary = "Unassign a Favorite Investor to a Profile", description = "Unassign a Favorite Investor to a Profile", tags = {"favorite-investors"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Favorite Investor Unassigned", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/profiles/{userId}/favoriteInvestors/{favoriteId}")
    public InvestorResource unassignProfileTag(@PathVariable Long userId, @PathVariable Long favoriteId) {
        return convertToResource(investorService.assignFavoriteInvestor(userId, favoriteId));
    }

    private Investor convertToEntity(SaveInvestorResource resource){
        return mapper.map(resource, Investor.class);
    }

    private InvestorResource convertToResource(Investor entity) {
        return mapper.map(entity, InvestorResource.class);
    }
}
