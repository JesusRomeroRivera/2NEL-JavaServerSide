package com.minka.tunel.service;

import com.minka.tunel.domain.model.Freelancer;
import com.minka.tunel.domain.repository.FreelancerRepository;
import com.minka.tunel.domain.repository.UserRepository;
import com.minka.tunel.domain.service.FreelancerService;
import com.minka.tunel.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FreelancerServiceImpl implements FreelancerService {

    @Autowired
    private FreelancerRepository freelancerRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<Freelancer> getAllFreelancers(Pageable pageable) {
        return freelancerRepository.findAll(pageable);    }

    @Override
    public Freelancer getFreelancerById(Long userId) {
        return freelancerRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Freelancer", "Id", userId));
    }

    @Override
    public Freelancer createFreelancer(Long userId, Freelancer freelancer) {
        var foundUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        foundUser.setProfile(freelancer);
        freelancer.setId(userId);
        freelancer.setUser(foundUser);
        return freelancerRepository.save(freelancer);
    }

    @Override
    public Freelancer updateFreelancer(Long userId, Freelancer freelancer) {
        return freelancerRepository.findById(userId)
                .map(freelancer1 -> {
                    freelancer1.setFirstName(freelancer.getFirstName());
                    freelancer1.setLastName(freelancer.getLastName());
                    freelancer1.setPortfolio(freelancer.getPortfolio());
                    freelancer.setSpecialty((freelancer.getSpecialty()));
                    return freelancerRepository.save(freelancer1);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Freelancer", "Id", userId));
    }

    @Override
    public ResponseEntity<?> deleteFreelancer(Long userId) {
        return freelancerRepository.findById(userId)
                .map(freelancer -> {
                    freelancerRepository.delete(freelancer);
                    return (ResponseEntity.ok().build());
                })
                .orElseThrow(() -> new ResourceNotFoundException("Freelancer", "Id", userId));
    }
}
