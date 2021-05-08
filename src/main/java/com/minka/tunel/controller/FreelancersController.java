package com.minka.tunel.controller;

import com.minka.tunel.domain.model.Entrepreneur;
import com.minka.tunel.domain.model.Freelancer;
import com.minka.tunel.domain.service.FreelancerService;
import com.minka.tunel.resource.EntrepreneurResource;
import com.minka.tunel.resource.FreelancerResource;
import com.minka.tunel.resource.SaveEntrepreneurResource;
import com.minka.tunel.resource.SaveFreelancerResource;
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

    @GetMapping("/freelancers")
    public Page<FreelancerResource> getAllFreelancers(Pageable pageable) {
        List<FreelancerResource> freelancers = freelancerService.getAllFreelancers(pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        int freelancersCount = freelancers.size();
        return new PageImpl<>(freelancers, pageable, freelancersCount);
    }

    @GetMapping("/freelancers/{userId}")
    public FreelancerResource getFreelancerById(
            @PathVariable Long userId) {
        return convertToResource(freelancerService.getFreelancerById(userId));
    }

    @PostMapping("/freelancers")
    public FreelancerResource createFreelancer(
            @Valid @RequestBody SaveFreelancerResource resource){
        return convertToResource(freelancerService.createFreelancer(convertToEntity(resource)));
    }

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
