package com.minka.tunel.domain.repository;

import com.minka.tunel.domain.model.Startup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StartupRepository extends JpaRepository<Startup, Long> {
    @Query(value = "SELECT * FROM startups s WHERE s.enterprise = :enterprise",nativeQuery = true)
    Optional<Startup> getStartupByEnterprise(@Param("enterprise") String enterprise);
}
