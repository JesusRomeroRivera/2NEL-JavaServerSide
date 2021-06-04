package com.minka.tunel.controller;

import com.minka.tunel.domain.model.Startup;
import com.minka.tunel.domain.service.StartupService;
import com.minka.tunel.resource.SaveStartupResource;
import com.minka.tunel.resource.StartupResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class EnterpriseStartupsController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private StartupService startupService;

    @Operation(summary = "Get Tree Leaves", description = "Get All Tree Leaves by ID and Pages", tags = {"tree-leaves"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Tree Leaves returned", content = @Content(mediaType = "application/json"))

    })
    @GetMapping("/enterprises/{enterpriseId}/startups")
    public Page<StartupResource> getAllLeavesByTreeId(@PathVariable Long enterpriseId, Pageable pageable) {
        Page<Startup> startupPage = startupService.getAllStartupsByEnterpriseId(enterpriseId, pageable);
        List<StartupResource> resources = startupPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get Leaf By ID", description = "Get Leaf by ID", tags = {"tree-leaves"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Leaf returned", content = @Content(mediaType = "application/json"))

    })
    @GetMapping("/enterprises/{enterpriseId}/startups/{startupId}")
    public StartupResource getLeafById(
            @PathVariable Long enterpriseId,
            @PathVariable Long startupId) {
        return convertToResource(startupService.getStartupById(enterpriseId, startupId));
    }

    @Operation(summary = "Create a Leaf", description = "Create a Leaf", tags = {"tree-leaves"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Leaf created", content = @Content(mediaType = "application/json"))

    })
    @PostMapping("/enterprises/{enterpriseId}/startups")
    public StartupResource createLeaf(@PathVariable Long enterpriseId, @Valid @RequestBody SaveStartupResource resource) {
        Startup startup = convertToEntity(resource);
        return convertToResource(startupService.createStartup(enterpriseId, startup));
    }

    @Operation(summary = "Update a Leaf", description = "Update a Leaf", tags = {"tree-leaves"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Leaf updated", content = @Content(mediaType = "application/json"))

    })
    @PutMapping("/enterprises/{enterpriseId}/startups/{startupId}")
    public StartupResource updateLeaf(@PathVariable Long enterpriseId, @PathVariable Long startupId, @RequestBody SaveStartupResource resource) {
        Startup startup = convertToEntity(resource);
        return convertToResource(startupService.updateStartup(enterpriseId, startupId, startup));
    }

    @Operation(summary = "Delete a Leaf", description = "Delete a Leaf", tags = {"tree-leaves"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Leaf deleted", content = @Content(mediaType = "application/json"))

    })
    @DeleteMapping("/enterprises/{enterpriseId}/startups/{startupId}")
    public ResponseEntity<?> deleteLeaf(@PathVariable Long enterpriseId, @PathVariable Long startupId) {
        return startupService.deleteStartup(enterpriseId, startupId);
    }

    private Startup convertToEntity(SaveStartupResource resource) {
        return mapper.map(resource, Startup.class);
    }

    private StartupResource convertToResource(Startup entity) {
        return mapper.map(entity, StartupResource.class);
    }
}
