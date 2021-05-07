package com.minka.tunel.domain.service;

import com.minka.tunel.domain.model.Investor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface InvestorService {
    Page<Investor> getAllInvestors(Pageable pageable);
    Investor getInvestorById(Long userId);
    Investor createInvestor(Investor investor);
    Investor updateInvestor(Long userId, Investor investor);
    ResponseEntity<?> deleteInvestor(Long userId);
}
