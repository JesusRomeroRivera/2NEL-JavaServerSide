package com.minka.tunel.domain.service;

import com.minka.tunel.domain.model.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface RequestService {
    Page<Request> getAllRequests(Pageable pageable);
    Page<Request> getAllRequestsByUserId(Long userId, Pageable pageable);
    Request getRequestById(Long requestId);
    Request createRequest(Request request);
    Request updateRequest(Long requestId, Request request);
    ResponseEntity<?> deleteRequest(Long requestId);
}
