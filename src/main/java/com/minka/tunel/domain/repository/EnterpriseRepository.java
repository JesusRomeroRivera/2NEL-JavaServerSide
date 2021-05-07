package com.minka.tunel.domain.repository;

import com.minka.tunel.domain.model.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {
}
