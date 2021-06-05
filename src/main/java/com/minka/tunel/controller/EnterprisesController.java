package com.minka.tunel.controller;

import com.minka.tunel.domain.model.Enterprise;
import com.minka.tunel.domain.service.EnterpriseService;
import com.minka.tunel.resource.EnterpriseResource;
import com.minka.tunel.resource.SaveEnterpriseResource;
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
public class EnterprisesController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private EnterpriseService enterpriseService;

    @Operation(summary = "Get Enterprises", description = "Get All Enterprises", tags = {"enterprises"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Enterprises returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/enterprises")
    public Page<EnterpriseResource> getAllEnterprises(Pageable pageable) {
        List<EnterpriseResource> enterprises = enterpriseService.getAllEnterprises(pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        int enterprisesCount = enterprises.size();
        return new PageImpl<>(enterprises, pageable, enterprisesCount);
    }

    @Operation(summary = "Get Enterprise by ID", description = "Get a specific Enterprise by its ID", tags = {"enterprises"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Enterprise returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/enterprises/{enterpriseId}")
    public EnterpriseResource getEnterpriseById(
            @PathVariable Long enterpriseId) {
        return convertToResource(enterpriseService.getEnterpriseById(enterpriseId));
    }

    @Operation(summary = "Create an Enterprise", description = "Create an Enterprise", tags = {"enterprises"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Enterprise created", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/enterprises")
    public EnterpriseResource createEnterprise(
            Long userId,
            @Valid @RequestBody SaveEnterpriseResource resource){
        return convertToResource(enterpriseService.createEnterprise(userId, convertToEntity(resource)));
    }

    @Operation(summary = "Update an Enterprise", description = "Update an Enterprise", tags = {"enterprises"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Enterprise updated", content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/enterprises/{enterpriseId}")
    public EnterpriseResource updateEnterprise(
            @PathVariable Long enterpriseId,
            @Valid @RequestBody SaveEnterpriseResource resource) {
        return convertToResource(enterpriseService.updateEnterprise(enterpriseId, convertToEntity(resource)));
    }

    @Operation(summary = "Delete an Enterprise", description = "Delete an Enterprise", tags = {"enterprises"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Enterprise deleted", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/enterprises/{enterpriseId}")
    public ResponseEntity<?> deleteEnterprise(@PathVariable Long enterpriseId) {
        return enterpriseService.deleteEnterprise(enterpriseId);
    }

    private Enterprise convertToEntity(SaveEnterpriseResource resource) {
        return mapper.map(resource, Enterprise.class);
    }

    private EnterpriseResource convertToResource(Enterprise entity) {
        return mapper.map(entity, EnterpriseResource.class);
    }
}
