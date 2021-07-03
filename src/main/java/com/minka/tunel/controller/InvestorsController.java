package com.minka.tunel.controller;

import com.minka.tunel.domain.model.Investor;
import com.minka.tunel.domain.service.InvestorService;
import com.minka.tunel.resource.*;
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

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class InvestorsController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private InvestorService investorService;

    @Operation(summary = "Get Investors", description = "Get All Investors", tags = {"investors"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Investors returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/investors")
    public Page<InvestorResource> getAllInvestors(Pageable pageable) {
        List<InvestorResource> investors = investorService.getAllInvestors(pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        int investorCount = investors.size();
        return new PageImpl<>(investors, pageable, investorCount);
    }

    @Operation(summary = "Get Investor by ID", description = "Get a specific Investor by its ID", tags = {"investors"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Investor returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/investors/{userId}")
    public InvestorResource getInvestorById(
            @PathVariable Long userId) {
        return convertToResource(investorService.getInvestorById(userId));
    }

    @Operation(summary = "Get Favorite Investors by UserID", description = "Get Favorite Investors by UserID", tags = {"investors"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Favorite Investors returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/profiles/{userId}/favoriteInvestors")
    public Page<InvestorResource> getFavoriteInvestorsByUserId(
            @PathVariable Long userId,
            Pageable pageable) {
        List<InvestorResource> investors = investorService.getAllFavoriteInvestorsByUserId(userId, pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        int investorsCount = investors.size();
        return new PageImpl<>(investors, pageable, investorsCount);
    }

    @Operation(summary = "Create an Investor", description = "Create an Investor", tags = {"investors"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Investor created", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/investors")
    public InvestorResource createInvestor(
            Long userId,
            @Valid @RequestBody SaveInvestorResource resource){
        return convertToResource(investorService.createInvestor(userId, convertToEntity(resource)));
    }

    @Operation(summary = "Update an Investor", description = "Update an Investor", tags = {"investors"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Investor updated", content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/investors/{userId}")
    public InvestorResource updateInvestor(
            @PathVariable Long userId,
            @Valid @RequestBody SaveInvestorResource resource) {
        return convertToResource(investorService.updateInvestor(userId, convertToEntity(resource)));
    }

    private Investor convertToEntity(SaveInvestorResource resource) {
        return mapper.map(resource, Investor.class);
    }

    private InvestorResource convertToResource(Investor entity) {
        return mapper.map(entity, InvestorResource.class);
    }
}
