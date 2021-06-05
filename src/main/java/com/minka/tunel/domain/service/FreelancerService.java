package com.minka.tunel.domain.service;

import com.minka.tunel.domain.model.Freelancer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface FreelancerService {
    Page<Freelancer> getAllFreelancers(Pageable pageable);
    Freelancer getFreelancerById(Long userId);
    Freelancer createFreelancer(Long userId, Freelancer freelancer);
    Freelancer updateFreelancer(Long userId, Freelancer freelancer);
    ResponseEntity<?> deleteFreelancer(Long userId);
}
