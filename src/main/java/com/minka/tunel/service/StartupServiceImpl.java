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
    public Page<Startup> getAllStartupsByEnterpriseId(Long enterpriseId, Pageable pageable) {
        return enterpriseRepository.findById(enterpriseId)
                .map(enterprise -> {
                    List<Startup> startups = enterprise.getStartups();
                    int startupsCount = startups.size();
                    return new PageImpl<>(startups, pageable, startupsCount);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Enterprise", "Id", enterpriseId));
    }

    @Override
    public Startup getStartupById(Long enterpriseId, Long startupId) {
        return startupRepository.findById(startupId)
                .orElseThrow(() -> new ResourceNotFoundException("Startup", "Id", startupId));    }

    @Override
    public Startup createStartup(Long enterpriseId, Startup startup) {
        var foundEnterprise = enterpriseRepository.findById(enterpriseId)
                .orElseThrow(() -> new ResourceNotFoundException("Enterprise", "Id", enterpriseId));

        startup.setEnterprise(foundEnterprise);
        foundEnterprise.getStartups().add(startup);
        return startupRepository.save(startup);
    }

    @Override
    public Startup updateStartup(Long enterpriseId, Long startupId, Startup startup) {
        var foundEnterprise = enterpriseRepository.findById(enterpriseId)
                .orElseThrow(() -> new ResourceNotFoundException("Enterprise", "Id", enterpriseId));

        return startupRepository.findById(startupId)
                .map(startup1 -> {
                    startup1.setName(startup.getName());
                    startup1.setDescription(startup.getDescription());
                    startup1.setImageUrl(startup.getImageUrl());
                    return startupRepository.save(startup1);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Startup", "Id", startupId));
    }

    @Override
    public ResponseEntity<?> deleteStartup(Long enterpriseId, Long startupId) {
        var foundEnterprise = enterpriseRepository.findById(enterpriseId)
                .orElseThrow(() -> new ResourceNotFoundException("Enterprise", "Id", enterpriseId));

        var foundStartup = foundEnterprise.getStartups().stream()
                .filter(startup -> startupId.equals(startup.getId()))
                .findFirst()
                .orElse(null);

        foundEnterprise.getStartups().remove(foundStartup);

        return startupRepository.findById(startupId)
                .map(tag -> {
                    startupRepository.delete(tag);
                    return (ResponseEntity.ok().build());
                })
                .orElseThrow(() -> new ResourceNotFoundException("Startup", "Id", startupId));
    }
}
