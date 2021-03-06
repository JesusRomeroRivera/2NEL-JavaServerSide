package com.minka.tunel.domain.repository;

import com.minka.tunel.domain.model.Investor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestorRepository extends JpaRepository<Investor, Long> {
}
