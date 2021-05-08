package com.minka.tunel.domain.repository;

import com.minka.tunel.domain.model.Entrepreneur;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EntrepreneurRepository extends JpaRepository<Entrepreneur, Long> {

}
