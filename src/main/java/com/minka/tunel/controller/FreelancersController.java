package com.minka.tunel.controller;

import com.minka.tunel.domain.model.Entrepreneur;
import com.minka.tunel.domain.model.Freelancer;
import com.minka.tunel.domain.service.FreelancerService;
import com.minka.tunel.resource.EntrepreneurResource;
import com.minka.tunel.resource.FreelancerResource;
import com.minka.tunel.resource.SaveEntrepreneurResource;
import com.minka.tunel.resource.SaveFreelancerResource;
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
@RequestMapping("/api")
public class FreelancersController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private FreelancerService freelancerService;

    @Operation(summary = "Get Freelancers", description = "Get All Freelancers", tags = {"freelancers"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Freelancers returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/freelancers")
    public Page<FreelancerResource> getAllFreelancers(Pageable pageable) {
        List<FreelancerResource> freelancers = freelancerService.getAllFreelancers(pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        int freelancersCount = freelancers.size();
        return new PageImpl<>(freelancers, pageable, freelancersCount);
    }

    @Operation(summary = "Get Freelancer by ID", description = "Get a specific Freelancer by its ID", tags = {"freelancers"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Freelancer returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/freelancers/{userId}")
    public FreelancerResource getFreelancerById(
            @PathVariable Long userId) {
        return convertToResource(freelancerService.getFreelancerById(userId));
    }

    @Operation(summary = "Create a Freelancer", description = "Create a Freelancer", tags = {"freelancers"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Freelancer created", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/freelancers")
    public FreelancerResource createFreelancer(
            @Valid @RequestBody SaveFreelancerResource resource){
        return convertToResource(freelancerService.createFreelancer(convertToEntity(resource)));
    }

    @Operation(summary = "Update a Freelancer", description = "Update a Freelancer", tags = {"freelancers"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Freelancer updated", content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/freelancers/{userId}")
    public FreelancerResource updateFreelancer(
            @PathVariable Long userId,
            @Valid @RequestBody SaveFreelancerResource resource) {
        return convertToResource(freelancerService.updateFreelancer(userId, convertToEntity(resource)));
    }

    private Freelancer convertToEntity(SaveFreelancerResource resource) {
        return mapper.map(resource, Freelancer.class);
    }

    private FreelancerResource convertToResource(Freelancer entity) {
        return mapper.map(entity, FreelancerResource.class);
    }
}
