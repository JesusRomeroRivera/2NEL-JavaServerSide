package com.minka.tunel.service;

import com.minka.tunel.domain.model.Request;
import com.minka.tunel.domain.repository.ProfileRepository;
import com.minka.tunel.domain.repository.RequestRepository;
import com.minka.tunel.domain.repository.UserRepository;
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
    private ProfileRepository profileRepository;

    @Override
    public Page<Request> getAllRequests(Pageable pageable) {
        return requestRepository.findAll(pageable);
    }

    @Override
    public Page<Request> getAllRequestsByUserId(Long userId, Pageable pageable) {
        return profileRepository.findById(userId)
                .map(request -> {
                    List<Request> requests = request.getRequests();
                    int requestsCount = requests.size();
                    return new PageImpl<>(requests, pageable, requestsCount);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Profile", "Id", userId));
    }

    @Override
    public Request getRequestById(Long requestId) {
        return requestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Request", "Id", requestId));
    }

    @Override
    public Request createRequest(Request request) {
        return requestRepository.save(request);
    }

    @Override
    public Request updateRequest(Long requestId, Request request) {
        return requestRepository.findById(requestId)
                .map(request1 -> {
                    request1.setSubject(request.getSubject());
                    return requestRepository.save(request1);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Request", "Id", requestId));
    }

    @Override
    public ResponseEntity<?> deleteRequest(Long requestId) {
        return requestRepository.findById(requestId)
                .map(request -> {
                    requestRepository.delete(request);
                    return (ResponseEntity.ok().build());
                })
                .orElseThrow(() -> new ResourceNotFoundException("Request", "Id", requestId));
    }
}
