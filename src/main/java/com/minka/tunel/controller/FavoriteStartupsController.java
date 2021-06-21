package com.minka.tunel.controller;

import com.minka.tunel.domain.model.Startup;
import com.minka.tunel.domain.service.StartupService;
import com.minka.tunel.resource.SaveStartupResource;
import com.minka.tunel.resource.StartupResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class FavoriteStartupsController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private StartupService startupService;

    private Startup convertToEntity(SaveStartupResource resource){
        return mapper.map(resource, Startup.class);
    }

    private StartupResource convertToResource(Startup entity) {
        return mapper.map(entity, StartupResource.class);
    }
}
