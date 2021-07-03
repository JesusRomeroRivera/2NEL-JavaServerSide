package com.minka.tunel.service;

import com.minka.tunel.domain.model.*;
import com.minka.tunel.domain.repository.*;
import com.minka.tunel.domain.service.InvestorService;
import com.minka.tunel.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestorServiceImpl implements InvestorService {

    @Autowired
    private InvestorRepository investorRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

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
    public Investor createInvestor(Long userId, Investor investor) {
        var foundUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        foundUser.setProfile(investor);
        investor.setId(userId);
        investor.setUser(foundUser);
        return investorRepository.save(investor);
    }

    @Override
    public Investor updateInvestor(Long userId, Investor investor) {
        return investorRepository.findById(userId)
                .map(investor1 -> {
                    investor1.setFirstName(investor.getFirstName());
                    investor1.setLastName(investor.getLastName());
                    investor1.setImageUrl(investor.getImageUrl());
                    investor1.setDescription(investor.getDescription());
                    investor1.setCity(investor.getCity());
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

    @Override
    public Page<Investor> getAllFavoriteInvestorsByUserId(Long userId, Pageable pageable) {
        return profileRepository.findById(userId)
                .map(user -> {
                    List<Investor> investors = user.getFavoriteInvestors();
                    int investorsCount = investors.size();
                    return new PageImpl<>(investors, pageable, investorsCount);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Investor", "Id", userId));
    }


}
