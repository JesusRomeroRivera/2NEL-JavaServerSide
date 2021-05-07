package com.minka.tunel.domain.repository;

import com.minka.tunel.domain.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {
}
