package com.minka.tunel.controller;

import com.minka.tunel.domain.model.Request;
import com.minka.tunel.domain.service.RequestService;
import com.minka.tunel.resource.RequestResource;
import com.minka.tunel.resource.SaveRequestResource;
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
@CrossOrigin
@RequestMapping("/api")
public class ProfileRequestsController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RequestService requestService;

    @Operation(summary = "Get Requests by UserID", description = "Get All Requests by UserId", tags = {"profile-requests"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Requests returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/profile/{userId}/requests")
    public Page<RequestResource> getAllRequestsByUserId(@PathVariable Long userId, Pageable pageable) {
        Page<Request> requestPage = requestService.getAllEntrepreneurRequestsByUserId(userId, pageable);
        List<RequestResource> resources = requestPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get Request By ID", description = "Get Request by ID", tags = {"profile-requests"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request returned", content = @Content(mediaType = "application/json"))

    })
    @GetMapping("/profile/{userId}/requests/{requestId}")
    public RequestResource getRequestById(
            @PathVariable Long userId,
            @PathVariable Long requestId) {
        return convertToResource(requestService.getEntrepreneurRequestById(userId, requestId));
    }

    @Operation(summary = "Create a Request", description = "Create a Request", tags = {"profile-requests"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request created", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/profile/{userId}/requests")
    public RequestResource createRequest(@PathVariable Long userId, @Valid @RequestBody SaveRequestResource resource) {
        Request request = convertToEntity(resource);
        return convertToResource(requestService.createEntrepreneurRequest(userId, request));
    }

    @Operation(summary = "Update a Request", description = "Update a Request", tags = {"profile-requests"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request updated", content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/profile/{userId}/requests/{requestId}")
    public RequestResource updateRequest(@PathVariable Long userId, @PathVariable Long requestId, @RequestBody SaveRequestResource resource) {
        Request request = convertToEntity(resource);
        return convertToResource(requestService.updateEntrepreneurRequest(userId, requestId, request));
    }

    @Operation(summary = "Delete a Request", description = "Delete a Request", tags = {"profile-requests"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request deleted", content = @Content(mediaType = "application/json"))

    })
    @DeleteMapping("/profile/{userId}/requests/{requestId}")
    public ResponseEntity<?> deleteRequest(@PathVariable Long userId, @PathVariable Long requestId) {
        return requestService.deleteEntrepreneurRequest(userId, requestId);
    }

    private Request convertToEntity(SaveRequestResource resource) {
        return mapper.map(resource, Request.class);
    }

    private RequestResource convertToResource(Request entity) {
        return mapper.map(entity, RequestResource.class);
    }
}
