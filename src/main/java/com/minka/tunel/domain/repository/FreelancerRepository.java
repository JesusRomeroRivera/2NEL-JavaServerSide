package com.minka.tunel.domain.repository;

import com.minka.tunel.domain.model.Entrepreneur;
import com.minka.tunel.domain.model.Freelancer;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface FreelancerRepository extends JpaRepository<Freelancer, Long> {
}
