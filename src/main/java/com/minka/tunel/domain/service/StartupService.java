package com.minka.tunel.domain.service;

import com.minka.tunel.domain.model.Startup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface StartupService {
    Page<Startup> getAllStartupsByEnterpriseId(Long enterpriseId, Pageable pageable);
    Startup getStartupById(Long enterpriseId, Long startupId);
    Startup createStartup(Long enterpriseId, Startup startup);
    Startup updateStartup(Long enterpriseId, Long startupId, Startup startup);
    ResponseEntity<?> deleteStartup(Long enterpriseId, Long startupId);
}
