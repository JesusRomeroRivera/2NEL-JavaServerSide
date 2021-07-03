package com.minka.tunel.controller;

import com.minka.tunel.domain.model.Entrepreneur;
import com.minka.tunel.domain.service.EntrepreneurService;
import com.minka.tunel.resource.EntrepreneurResource;
import com.minka.tunel.resource.SaveEntrepreneurResource;
import com.minka.tunel.resource.TagResource;
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
public class EntrepreneursController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private EntrepreneurService entrepreneurService;

    @Operation(summary = "Get Entrepreneurs", description = "Get All Entrepreneurs", tags = {"entrepreneurs"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Entrepreneurs returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/entrepreneurs")
    public Page<EntrepreneurResource> getAllEntrepreneurs(Pageable pageable) {
        List<EntrepreneurResource> entrepreneurs = entrepreneurService.getAllEntrepreneurs(pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        int entrepreneursCount = entrepreneurs.size();
        return new PageImpl<>(entrepreneurs, pageable, entrepreneursCount);
    }

    @Operation(summary = "Get Favorite Entrepreneurs by UserID", description = "Get Favorite Entrepreneurs by UserID", tags = {"entrepreneurs"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Favorite Entrepreneurs returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/profiles/{userId}/favoriteEntrepreneurs")
    public Page<EntrepreneurResource> getFavoriteEntrepreneursByUserId(
            @PathVariable Long userId,
            Pageable pageable) {
        List<EntrepreneurResource> entrepreneurs = entrepreneurService.getAllFavoriteEntrepreneursByUserId(userId, pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        int entrepreneursCount = entrepreneurs.size();
        return new PageImpl<>(entrepreneurs, pageable, entrepreneursCount);
    }

    @Operation(summary = "Get Entrepreneur by ID", description = "Get a specific Entrepreneur by its ID", tags = {"entrepreneurs"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entrepreneur returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/entrepreneurs/{userId}")
    public EntrepreneurResource getEntrepreneurById(
            @PathVariable Long userId) {
        return convertToResource(entrepreneurService.getEntrepreneurById(userId));
    }

    @Operation(summary = "Create an Entrepreneur", description = "Create an Entrepreneur", tags = {"entrepreneurs"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entrepreneur created", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/entrepreneurs")
    public EntrepreneurResource createEntrepreneur(
            Long userId,
            @Valid @RequestBody SaveEntrepreneurResource resource){
        return convertToResource(entrepreneurService.createEntrepreneur(userId, convertToEntity(resource)));
    }

    @Operation(summary = "Update an Entrepreneur", description = "Update an Entrepreneur", tags = {"entrepreneurs"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entrepreneur updated", content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/entrepreneurs/{userId}")
    public EntrepreneurResource updateEntrepreneur(
            @PathVariable Long userId,
            @Valid @RequestBody SaveEntrepreneurResource resource) {
        return convertToResource(entrepreneurService.updateEntrepreneur(userId, convertToEntity(resource)));
    }

    private Entrepreneur convertToEntity(SaveEntrepreneurResource resource) {
        return mapper.map(resource, Entrepreneur.class);
    }

    private EntrepreneurResource convertToResource(Entrepreneur entity) {
        return mapper.map(entity, EntrepreneurResource.class);
    }
}
