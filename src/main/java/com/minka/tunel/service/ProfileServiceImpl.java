package com.minka.tunel.service;

import com.minka.tunel.domain.model.*;
import com.minka.tunel.domain.repository.*;
import com.minka.tunel.domain.service.ProfileService;
import com.minka.tunel.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private EntrepreneurRepository entrepreneurRepository;

    @Autowired
    private FreelancerRepository freelancerRepository;

    @Autowired
    private InvestorRepository investorRepository;

    @Autowired
    private TagRepository tagRepository;

    @Override
    public Page<Profile> getAllProfiles(Pageable pageable) {
        return profileRepository.findAll(pageable);
    }

    @Override
    public Profile getProfileById(Long userId) {
        return profileRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile", "Id", userId));
    }

    @Override
    public Profile createProfile(Profile profile) {
        return profileRepository.save(profile);    }

    @Override
    public Profile updateProfile(Long userId, Profile profile) {
        return profileRepository.findById(userId)
                .map(profile1 -> {
                    profile1.setFirstName(profile.getFirstName());
                    profile1.setLastName(profile.getLastName());
                    return profileRepository.save(profile1);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Profile", "Id", userId));
    }

    @Override
    public ResponseEntity<?> deleteProfile(Long userId) {
        return profileRepository.findById(userId)
                .map(profile -> {
                    profileRepository.delete(profile);
                    return (ResponseEntity.ok().build());
                })
                .orElseThrow(() -> new ResourceNotFoundException("Profile", "Id", userId));
    }

    @Override
    public Profile assignProfileTag(Long profileId, Long tagId) {
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Tag", "Id", tagId));
        return profileRepository.findById(profileId).map(
                profile -> profileRepository.save(profile.tagWith(tag)))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Tag", "Id", tagId));
    }

    @Override
    public Profile unassignProfileTag(Long profileId, Long tagId) {
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Tag", "Id", tagId));
        return profileRepository.findById(profileId).map(
                profile -> profileRepository.save(profile.unTagWith(tag)))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Tag", "Id", tagId));
    }

    @Override
    public Profile assignFavoriteEntrepreneur(Long userId, Long favoriteId) {
        Entrepreneur entrepreneur = entrepreneurRepository.findById(favoriteId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Entrepreneur", "Id", userId));
        return profileRepository.findById(userId).map(
                profile -> profileRepository.save(profile.addFavoriteEntrepreneur(entrepreneur)))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Profile", "Id", userId));
    }

    @Override
    public Profile unassignFavoriteEntrepreneur(Long userId, Long favoriteId) {
        Entrepreneur entrepreneur = entrepreneurRepository.findById(favoriteId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Entrepreneur", "Id", userId));
        return profileRepository.findById(userId).map(
                profile -> profileRepository.save(profile.removeFavoriteEntrepreneur(entrepreneur)))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Profile", "Id", userId));
    }

    @Override
    public Profile assignFavoriteFreelancer(Long userId, Long favoriteId) {
        Freelancer freelancer = freelancerRepository.findById(favoriteId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Freelancer", "Id", userId));
        return profileRepository.findById(userId).map(
                profile -> profileRepository.save(profile.addFavoriteFreelancer(freelancer)))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Profile", "Id", userId));
    }

    @Override
    public Profile unassignFavoriteFreelancer(Long userId, Long favoriteId) {
        Freelancer freelancer = freelancerRepository.findById(favoriteId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Freelancer", "Id", userId));
        return profileRepository.findById(userId).map(
                profile -> profileRepository.save(profile.removeFavoriteFreelancer(freelancer)))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Profile", "Id", userId));
    }

    @Override
    public Profile assignFavoriteInvestor(Long userId, Long favoriteId) {
        Investor investor = investorRepository.findById(favoriteId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Investor", "Id", userId));
        return profileRepository.findById(userId).map(
                profile -> profileRepository.save(profile.addFavoriteInvestor(investor)))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Profile", "Id", userId));
    }

    @Override
    public Profile unassignFavoriteInvestor(Long userId, Long favoriteId) {
        Investor investor = investorRepository.findById(favoriteId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Investor", "Id", userId));
        return profileRepository.findById(userId).map(
                profile -> profileRepository.save(profile.removeFavoriteInvestor(investor)))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Profile", "Id", userId));
    }
}
