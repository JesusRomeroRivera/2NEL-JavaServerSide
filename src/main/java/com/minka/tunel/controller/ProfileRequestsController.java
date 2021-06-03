package com.minka.tunel.controller;

import com.minka.tunel.domain.model.Request;
import com.minka.tunel.domain.service.RequestService;
import com.minka.tunel.resource.RequestResource;
import com.minka.tunel.resource.SaveRequestResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProfileRequestsController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RequestService requestService;

    private Request convertToEntity(SaveRequestResource resource) {
        return mapper.map(resource, Request.class);
    }

    private RequestResource convertToResource(Request entity) {
        return mapper.map(entity, RequestResource.class);
    }
}
