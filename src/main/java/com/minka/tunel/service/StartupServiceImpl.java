package com.minka.tunel.service;

import com.minka.tunel.domain.model.Startup;
import com.minka.tunel.domain.repository.EnterpriseRepository;
import com.minka.tunel.domain.repository.StartupRepository;
import com.minka.tunel.domain.service.StartupService;
import com.minka.tunel.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StartupServiceImpl implements StartupService {

    @Autowired
    private StartupRepository startupRepository;

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Override
    public Page<Startup> getAllStartups(Pageable pageable) {
        return startupRepository.findAll(pageable);
    }

    @Override
    public Page<Startup> getAllStartupsByUserId(Long userId, Pageable pageable) {
        return enterpriseRepository.findById(userId)
                .map(enterprise -> {
                    List<Startup> startups = enterprise.getStartups();
                    int startupsCount = startups.size();
                    return new PageImpl<>(startups, pageable, startupsCount);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Enterprise", "Id", userId));
    }

    @Override
    public Startup getStartupById(Long startupId) {
        return startupRepository.findById(startupId)
                .orElseThrow(() -> new ResourceNotFoundException("Startup", "Id", startupId));    }

    @Override
    public Startup createStartup(Startup startup) {
        return startupRepository.save(startup);
    }

    @Override
    public Startup updateStartup(Long startupId, Startup startup) {
        return startupRepository.findById(startupId)
                .map(startup1 -> {
                    startup1.setName(startup.getName());
                    startup1.setDescription(startup.getDescription());
                    return startupRepository.save(startup1);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Startup", "Id", startupId));
    }

    @Override
    public ResponseEntity<?> deleteStartup(Long startupId) {
        return startupRepository.findById(startupId)
                .map(tag -> {
                    startupRepository.delete(tag);
                    return (ResponseEntity.ok().build());
                })
                .orElseThrow(() -> new ResourceNotFoundException("Startup", "Id", startupId));
    }
}
