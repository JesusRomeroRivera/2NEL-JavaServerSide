package com.minka.tunel.domain.service;

import com.minka.tunel.domain.model.Enterprise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface EnterpriseService {
    Page<Enterprise> getAllEnterprises(Pageable pageable);
    Enterprise getEnterpriseById(Long enterpriseId);
    Enterprise createEnterprise(Long userId, Enterprise enterprise);
    Enterprise updateEnterprise(Long enterpriseId, Enterprise enterprise);
    ResponseEntity<?> deleteEnterprise(Long enterpriseId);
}
