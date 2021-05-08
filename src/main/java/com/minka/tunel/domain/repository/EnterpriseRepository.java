package com.minka.tunel.domain.repository;

import com.minka.tunel.domain.model.Enterprise;
import com.minka.tunel.domain.model.Entrepreneur;
import net.bytebuddy.asm.Advice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {
    @Query(value = "SELECT * FROM enterprises e WHERE e.entrepreneur = :entrepreneur",nativeQuery = true)
    Optional<Enterprise> getEnterpriseByEntrepreneur(@Param("entrepreneur") String entrepreneur);
}
