package com.minka.tunel.domain.service;

import com.minka.tunel.domain.model.Entrepreneur;
import com.minka.tunel.domain.model.Freelancer;
import com.minka.tunel.domain.model.Investor;
import com.minka.tunel.domain.model.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface FreelancerService {
    Page<Freelancer> getAllFreelancers(Pageable pageable);
    Page<Freelancer> getAllFavoriteFreelancersByUserId(Long userId, Pageable pageable);
    Freelancer getFreelancerById(Long userId);
    Freelancer createFreelancer(Long userId, Freelancer freelancer);
    Freelancer updateFreelancer(Long userId, Freelancer freelancer);
    ResponseEntity<?> deleteFreelancer(Long userId);

}
