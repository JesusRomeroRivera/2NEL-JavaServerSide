package com.minka.tunel.controller;

import com.minka.tunel.domain.model.Request;
import com.minka.tunel.domain.model.Tag;
import com.minka.tunel.domain.service.RequestService;
import com.minka.tunel.resource.RequestResource;
import com.minka.tunel.resource.SaveRequestResource;
import com.minka.tunel.resource.SaveTagResource;
import com.minka.tunel.resource.TagResource;
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
public class RequestsController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private RequestService requestService;

    @GetMapping("/requests")
    public Page<RequestResource> getAllRequests(Pageable pageable) {
        List<RequestResource> requests = requestService.getAllRequests(pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        int requestsCount = requests.size();
        return new PageImpl<>(requests, pageable, requestsCount);
    }

    @GetMapping("/profiles/{userId}/requests")
    public Page<RequestResource> getAllRequestsByUserId(
            @PathVariable Long userId,
            Pageable pageable) {
        List<RequestResource> requests = requestService.getAllRequestsByUserId(userId, pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        int requestsCount = requests.size();
        return new PageImpl<>(requests, pageable, requestsCount);
    }

    @GetMapping("/requests/{requestId}")
    public RequestResource getRequestById(
            @PathVariable Long requestId) {
        return convertToResource(requestService.getRequestById(requestId));
    }

    @PostMapping("/requests")
    public RequestResource createRequest(
            @Valid @RequestBody SaveRequestResource resource){
        return convertToResource(requestService.createRequest(convertToEntity(resource)));
    }

    @PutMapping("/requests/{requestId}")
    public RequestResource updateRequest(
            @PathVariable Long requestId,
            @Valid @RequestBody SaveRequestResource resource) {
        return convertToResource(requestService.updateRequest(requestId, convertToEntity(resource)));
    }

    @DeleteMapping("/requests/{requestId}")
    public ResponseEntity<?> deleteRequest(@PathVariable Long requestId) {
        return requestService.deleteRequest(requestId);
    }

    private Request convertToEntity(SaveRequestResource resource) {
        return mapper.map(resource, Request.class);
    }

    private RequestResource convertToResource(Request entity) {
        return mapper.map(entity, RequestResource.class);
    }
}
