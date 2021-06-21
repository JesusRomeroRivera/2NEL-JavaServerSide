package com.minka.tunel.domain.service;

import com.minka.tunel.domain.model.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface RequestService {
    Page<Request> getAllEntrepreneurRequestsByUserId(Long userId, Pageable pageable);
    Page<Request> getAllFreelancerRequestsByUserId(Long userId, Pageable pageable);
    Page<Request> getAllInvestorRequestsByUserId(Long userId, Pageable pageable);
    Request getEntrepreneurRequestById(Long userId, Long requestId);
    Request getFreelancerRequestById(Long userId, Long requestId);
    Request getInvestorRequestById(Long userId, Long requestId);
    Request createEntrepreneurRequest(Long userId, Request request);
    Request createFreelancerRequest(Long userId, Request request);
    Request createInvestorRequest(Long userId, Request request);
    Request updateEntrepreneurRequest(Long userId, Long requestId, Request request);
    Request updateFreelancerRequest(Long userId, Long requestId, Request request);
    Request updateInvestorRequest(Long userId, Long requestId, Request request);
    ResponseEntity<?> deleteEntrepreneurRequest(Long userId, Long requestId);
    ResponseEntity<?> deleteFreelancerRequest(Long userId, Long requestId);
    ResponseEntity<?> deleteInvestorRequest(Long userId, Long requestId);
}
