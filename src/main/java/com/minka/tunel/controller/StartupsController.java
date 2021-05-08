package com.minka.tunel.controller;

import com.minka.tunel.domain.model.Request;
import com.minka.tunel.domain.model.Startup;
import com.minka.tunel.domain.service.StartupService;
import com.minka.tunel.resource.RequestResource;
import com.minka.tunel.resource.SaveRequestResource;
import com.minka.tunel.resource.SaveStartupResource;
import com.minka.tunel.resource.StartupResource;
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
public class StartupsController {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private StartupService startupService;

    @GetMapping("/startups")
    public Page<StartupResource> getAllStartups(Pageable pageable) {
        List<StartupResource> startups = startupService.getAllStartups(pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        int startupsCount = startups.size();
        return new PageImpl<>(startups, pageable, startupsCount);
    }

    @GetMapping("/enterprises/{userId}/startups")
    public Page<StartupResource> getAllStartupsByUserId(
            @PathVariable Long userId,
            Pageable pageable) {
        List<StartupResource> startups = startupService.getAllStartupsByUserId(userId, pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        int startupsCount = startups.size();
        return new PageImpl<>(startups, pageable, startupsCount);
    }

    @GetMapping("/startups/{userId}")
    public StartupResource getStartupById(
            @PathVariable Long userId) {
        return convertToResource(startupService.getStartupById(userId));
    }

    @PostMapping("/startups")
    public StartupResource createStartup(
            @Valid @RequestBody SaveStartupResource resource){
        return convertToResource(startupService.createStartup(convertToEntity(resource)));
    }

    @PutMapping("/startups/{userId}")
    public StartupResource updateStartup(
            @PathVariable Long userId,
            @Valid @RequestBody SaveStartupResource resource) {
        return convertToResource(startupService.updateStartup(userId, convertToEntity(resource)));
    }

    private Startup convertToEntity(SaveStartupResource resource) {
        return mapper.map(resource, Startup.class);
    }

    private StartupResource convertToResource(Startup entity) {
        return mapper.map(entity, StartupResource.class);
    }
}
