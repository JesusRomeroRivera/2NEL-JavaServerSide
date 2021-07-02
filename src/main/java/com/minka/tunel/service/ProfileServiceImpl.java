package com.minka.tunel.service;

import com.minka.tunel.domain.model.Profile;
import com.minka.tunel.domain.model.Tag;
import com.minka.tunel.domain.repository.ProfileRepository;
import com.minka.tunel.domain.repository.TagRepository;
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
}
