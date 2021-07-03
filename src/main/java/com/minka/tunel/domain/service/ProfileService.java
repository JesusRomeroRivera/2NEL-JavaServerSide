package com.minka.tunel.domain.service;

import com.minka.tunel.domain.model.Entrepreneur;
import com.minka.tunel.domain.model.Freelancer;
import com.minka.tunel.domain.model.Investor;
import com.minka.tunel.domain.model.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ProfileService {
    Page<Profile> getAllProfiles(Pageable pageable);
    Profile getProfileById(Long userId);
    Profile createProfile(Profile profile);
    Profile updateProfile(Long userId, Profile profile);
    ResponseEntity<?> deleteProfile(Long userId);
    Profile assignProfileTag(Long profileId, Long tagId);
    Profile unassignProfileTag(Long profileId, Long tagId);
    Profile assignFavoriteEntrepreneur(Long userId, Long favoriteId);
    Profile unassignFavoriteEntrepreneur(Long userId, Long favoriteId);
    Profile assignFavoriteFreelancer(Long userId, Long favoriteId);
    Profile unassignFavoriteFreelancer(Long userId, Long favoriteId);
    Profile assignFavoriteInvestor(Long userId, Long favoriteId);
    Profile unassignFavoriteInvestor(Long userId, Long favoriteId);
}
