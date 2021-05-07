package com.minka.tunel.domain.service;

import com.minka.tunel.domain.model.Startup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface StartupService {
    Page<Startup> getAllStartups(Pageable pageable);
    Page<Startup> getAllStartupsByUserId(Long userId, Pageable pageable);
    Startup getStartupById(Long startupId);
    Startup createStartup(Startup startup);
    Startup updateStartup(Long startupId, Startup startup);
    ResponseEntity<?> deleteStartup(Long startupId);
}
