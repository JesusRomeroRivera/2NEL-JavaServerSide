package com.minka.tunel.service;

import com.minka.tunel.domain.model.Investor;
import com.minka.tunel.domain.repository.InvestorRepository;
import com.minka.tunel.domain.service.InvestorService;
import com.minka.tunel.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class InvestorServiceImpl implements InvestorService {

    @Autowired
    private InvestorRepository investorRepository;

    @Override
    public Page<Investor> getAllInvestors(Pageable pageable) {
        return investorRepository.findAll(pageable);
    }

    @Override
    public Investor getInvestorById(Long userId) {
        return investorRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Investor", "Id", userId));
    }

    @Override
    public Investor createInvestor(Investor investor) {
        return investorRepository.save(investor);
    }

    @Override
    public Investor updateInvestor(Long userId, Investor investor) {
        return investorRepository.findById(userId)
                .map(investor1 -> {
                    investor1.setFirstName(investor.getFirstName());
                    investor1.setLastName(investor.getLastName());
                    investor1.setPortfolio(investor.getPortfolio());
                    return investorRepository.save(investor1);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Investor", "Id", userId));
    }

    @Override
    public ResponseEntity<?> deleteInvestor(Long userId) {
        return investorRepository.findById(userId)
                .map(investor -> {
                    investorRepository.delete(investor);
                    return (ResponseEntity.ok().build());
                })
                .orElseThrow(() -> new ResourceNotFoundException("Investor", "Id", userId));
    }
}
