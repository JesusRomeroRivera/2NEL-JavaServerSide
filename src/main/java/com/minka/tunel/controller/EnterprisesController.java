package com.minka.tunel.controller;

import com.minka.tunel.domain.model.Enterprise;
import com.minka.tunel.domain.model.Tag;
import com.minka.tunel.domain.service.EnterpriseService;
import com.minka.tunel.resource.EnterpriseResource;
import com.minka.tunel.resource.SaveEnterpriseResource;
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
public class EnterprisesController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private EnterpriseService enterpriseService;

    @GetMapping("/enterprises")
    public Page<EnterpriseResource> getAllEnterprises(Pageable pageable) {
        List<EnterpriseResource> enterprises = enterpriseService.getAllEnterprises(pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        int enterprisesCount = enterprises.size();
        return new PageImpl<>(enterprises, pageable, enterprisesCount);
    }

    @GetMapping("/enterprises/{enterpriseId}")
    public EnterpriseResource getEnterpriseById(
            @PathVariable Long enterpriseId) {
        return convertToResource(enterpriseService.getEnterpriseById(enterpriseId));
    }

    @PostMapping("/enterprises")
    public EnterpriseResource createEnterprise(
            @Valid @RequestBody SaveEnterpriseResource resource){
        return convertToResource(enterpriseService.createEnterprise(convertToEntity(resource)));
    }

    @PutMapping("/enterprises/{enterpriseId}")
    public EnterpriseResource updateEnterprise(
            @PathVariable Long enterpriseId,
            @Valid @RequestBody SaveEnterpriseResource resource) {
        return convertToResource(enterpriseService.updateEnterprise(enterpriseId, convertToEntity(resource)));
    }

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
