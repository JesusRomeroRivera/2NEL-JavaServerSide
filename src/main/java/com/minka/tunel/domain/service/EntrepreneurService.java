package com.minka.tunel.domain.service;

import com.minka.tunel.domain.model.Entrepreneur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface EntrepreneurService {
    Page<Entrepreneur> getAllEntrepreneurs(Pageable pageable);
    Entrepreneur getEntrepreneurById(Long userId);
    Entrepreneur createEntrepreneur(Entrepreneur entrepreneur);
    Entrepreneur updateEntrepreneur(Long userId, Entrepreneur entrepreneur);
    ResponseEntity<?> deleteEntrepreneur(Long userId);
}