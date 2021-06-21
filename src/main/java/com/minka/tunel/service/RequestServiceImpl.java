package com.minka.tunel.service;

import com.minka.tunel.domain.model.Request;
import com.minka.tunel.domain.repository.*;
import com.minka.tunel.domain.service.RequestService;
import com.minka.tunel.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private EntrepreneurRepository entrepreneurRepository;

    @Autowired
    private FreelancerRepository freelancerRepository;

    @Autowired
    private InvestorRepository investorRepository;

    @Override
    public Page<Request> getAllEntrepreneurRequestsByUserId(Long userId, Pageable pageable) {
        return entrepreneurRepository.findById(userId)
                .map(request -> {
                    List<Request> requests = request.getRequests();
                    int requestsCount = requests.size();
                    return new PageImpl<>(requests, pageable, requestsCount);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Entrepreneur", "Id", userId));
    }

    @Override
    public Page<Request> getAllFreelancerRequestsByUserId(Long userId, Pageable pageable) {
        return freelancerRepository.findById(userId)
                .map(request -> {
                    List<Request> requests = request.getRequests();
                    int requestsCount = requests.size();
                    return new PageImpl<>(requests, pageable, requestsCount);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Freelancer", "Id", userId));
    }

    @Override
    public Page<Request> getAllInvestorRequestsByUserId(Long userId, Pageable pageable) {
        return investorRepository.findById(userId)
                .map(request -> {
                    List<Request> requests = request.getRequests();
                    int requestsCount = requests.size();
                    return new PageImpl<>(requests, pageable, requestsCount);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Investor", "Id", userId));
    }

    @Override
    public Request getEntrepreneurRequestById(Long userId, Long requestId) {
        var foundEntrepreneur = entrepreneurRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Entrepreneur", "Id", userId));

        return requestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Request", "Id", requestId));
    }

    @Override
    public Request getFreelancerRequestById(Long userId, Long requestId) {
        var foundFreelancer = freelancerRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Freelancer", "Id", userId));

        return requestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Request", "Id", requestId));
    }

    @Override
    public Request getInvestorRequestById(Long userId, Long requestId) {
        var foundInvestor = investorRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Investor", "Id", userId));

        return requestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Request", "Id", requestId));
    }

    @Override
    public Request createEntrepreneurRequest(Long userId, Request request) {
        var foundEntrepreneur = entrepreneurRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Entrepreneur", "Id", userId));

        //request.set

        return requestRepository.save(request);
    }

    @Override
    public Request createFreelancerRequest(Long userId, Request request) {
        return null;
    }

    @Override
    public Request createInvestorRequest(Long userId, Request request) {
        return null;
    }

    @Override
    public Request updateEntrepreneurRequest(Long userId, Long requestId, Request request) {
        return requestRepository.findById(requestId)
                .map(request1 -> {
                    request1.setSubject(request.getSubject());
                    return requestRepository.save(request1);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Request", "Id", requestId));
    }

    @Override
    public Request updateFreelancerRequest(Long userId, Long requestId, Request request) {
        return null;
    }

    @Override
    public Request updateInvestorRequest(Long userId, Long requestId, Request request) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteEntrepreneurRequest(Long userId, Long requestId) {
        return requestRepository.findById(requestId)
                .map(request -> {
                    requestRepository.delete(request);
                    return (ResponseEntity.ok().build());
                })
                .orElseThrow(() -> new ResourceNotFoundException("Request", "Id", requestId));
    }

    @Override
    public ResponseEntity<?> deleteFreelancerRequest(Long userId, Long requestId) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteInvestorRequest(Long userId, Long requestId) {
        return null;
    }
}
