package com.minka.tunel.service;

import com.minka.tunel.domain.model.Entrepreneur;
import com.minka.tunel.domain.model.Freelancer;
import com.minka.tunel.domain.model.Tag;
import com.minka.tunel.domain.repository.EntrepreneurRepository;
import com.minka.tunel.domain.repository.ProfileRepository;
import com.minka.tunel.domain.repository.UserRepository;
import com.minka.tunel.domain.service.EntrepreneurService;
import com.minka.tunel.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntrepreneurServiceImpl implements EntrepreneurService {

    @Autowired
    private EntrepreneurRepository entrepreneurRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<Entrepreneur> getAllEntrepreneurs(Pageable pageable) {
        return entrepreneurRepository.findAll(pageable);
    }

    @Override
    public Page<Entrepreneur> getAllFavoriteEntrepreneursByUserId(Long userId, Pageable pageable) {
        return profileRepository.findById(userId)
                .map(user -> {
                    List<Entrepreneur> entrepreneurs = user.getFavoriteEntrepreneurs();
                    int entrepreneursCount = entrepreneurs.size();
                    return new PageImpl<>(entrepreneurs, pageable, entrepreneursCount);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Entrepreneur", "Id", userId));
    }

    @Override
    public Entrepreneur getEntrepreneurById(Long userId) {
        return entrepreneurRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Entrepreneur", "Id", userId));
    }

    @Override
    public Entrepreneur createEntrepreneur(Long userId, Entrepreneur entrepreneur) {
        var foundUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        foundUser.setProfile(entrepreneur);
        entrepreneur.setId(userId);
        entrepreneur.setUser(foundUser);
        return entrepreneurRepository.save(entrepreneur);
    }

    @Override
    public Entrepreneur updateEntrepreneur(Long userId, Entrepreneur entrepreneur) {
        return entrepreneurRepository.findById(userId)
                .map(entrepreneur1 -> {
                    entrepreneur1.setFirstName(entrepreneur.getFirstName());
                    entrepreneur1.setLastName(entrepreneur.getLastName());
                    entrepreneur1.setImageUrl(entrepreneur.getImageUrl());
                    entrepreneur1.setDescription(entrepreneur.getDescription());
                    entrepreneur1.setCity(entrepreneur.getCity());
                    return entrepreneurRepository.save(entrepreneur1);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Entrepreneur", "Id", userId));
    }

    @Override
    public ResponseEntity<?> deleteEntrepreneur(Long userId) {
        return entrepreneurRepository.findById(userId)
                .map(entrepreneur -> {
                    entrepreneurRepository.delete(entrepreneur);
                    return (ResponseEntity.ok().build());
                })
                .orElseThrow(() -> new ResourceNotFoundException("Entrepreneur", "Id", userId));
    }
}
