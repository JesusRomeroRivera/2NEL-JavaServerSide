package com.minka.tunel.service;

import com.minka.tunel.domain.model.Enterprise;
import com.minka.tunel.domain.repository.EnterpriseRepository;
import com.minka.tunel.domain.service.EnterpriseService;
import com.minka.tunel.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EnterpriseServiceImpl implements EnterpriseService {

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Override
    public Page<Enterprise> getAllEnterprises(Pageable pageable) {
        return enterpriseRepository.findAll(pageable);
    }

    @Override
    public Enterprise getEnterpriseById(Long enterpriseId) {
        return enterpriseRepository.findById(enterpriseId)
                .orElseThrow(() -> new ResourceNotFoundException("Enterprise", "Id", enterpriseId));
    }

    @Override
    public Enterprise createTag(Enterprise enterprise) {
        return enterpriseRepository.save(enterprise);
    }

    @Override
    public Enterprise updateTag(Long enterpriseId, Enterprise enterprise) {
        return enterpriseRepository.findById(enterpriseId)
                .map(enterprise1 -> {
                    enterprise1.setName(enterprise.getName());
                    enterprise1.setBusinessEmail(enterprise.getBusinessEmail());
                    enterprise1.setCorpNumber(enterprise.getCorpNumber());
                    enterprise1.setDescription(enterprise.getDescription());
                    return enterpriseRepository.save(enterprise1);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Enterprise", "Id", enterpriseId));
    }

    @Override
    public ResponseEntity<?> deleteTag(Long enterpriseId) {
        return enterpriseRepository.findById(enterpriseId)
                .map(tag -> {
                    enterpriseRepository.delete(tag);
                    return (ResponseEntity.ok().build());
                })
                .orElseThrow(() -> new ResourceNotFoundException("Enterprise", "Id", enterpriseId));
    }
}
