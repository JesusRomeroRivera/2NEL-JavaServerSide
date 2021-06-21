package com.minka.tunel.domain.service;

import com.minka.tunel.domain.model.Entrepreneur;
import com.minka.tunel.domain.model.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface EntrepreneurService {
    Page<Entrepreneur> getAllEntrepreneurs(Pageable pageable);
    Entrepreneur getEntrepreneurById(Long userId);
    Entrepreneur createEntrepreneur(Long userId, Entrepreneur entrepreneur);
    Entrepreneur updateEntrepreneur(Long userId, Entrepreneur entrepreneur);
    ResponseEntity<?> deleteEntrepreneur(Long userId);
    Entrepreneur assignFavoriteEntrepreneur(Long userId, Long favoriteId);
    Entrepreneur unassignFavoriteEntrepreneur(Long userId, Long favoriteId);
}
