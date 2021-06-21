package com.minka.tunel.domain.service;

import com.minka.tunel.domain.model.Freelancer;
import com.minka.tunel.domain.model.Investor;
import com.minka.tunel.domain.model.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface InvestorService {
    Page<Investor> getAllInvestors(Pageable pageable);
    Investor getInvestorById(Long userId);
    Investor createInvestor(Long userId, Investor investor);
    Investor updateInvestor(Long userId, Investor investor);
    ResponseEntity<?> deleteInvestor(Long userId);
    Investor assignFavoriteInvestor(Long userId, Long favoriteId);
    Investor unassignFavoriteInvestor(Long userId, Long favoriteId);
}
