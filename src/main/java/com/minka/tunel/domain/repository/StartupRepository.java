package com.minka.tunel.domain.repository;

import com.minka.tunel.domain.model.Startup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StartupRepository extends JpaRepository<Startup, Long> {
}
