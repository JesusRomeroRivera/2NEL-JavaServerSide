package com.minka.tunel.domain.service;

import com.minka.tunel.domain.model.Freelancer;
import com.minka.tunel.domain.model.Investor;
import com.minka.tunel.domain.model.Profile;
import com.minka.tunel.domain.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface InvestorService {
    Page<Investor> getAllInvestors(Pageable pageable);
    Page<Investor> getAllFavoriteInvestorsByUserId(Long userId, Pageable pageable);
    Investor getInvestorById(Long userId);
    Investor createInvestor(Long userId, Investor investor);
    Investor updateInvestor(Long userId, Investor investor);
    ResponseEntity<?> deleteInvestor(Long userId);

}
