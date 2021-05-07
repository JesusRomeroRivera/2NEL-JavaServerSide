package com.minka.tunel.domain.service;

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
}
