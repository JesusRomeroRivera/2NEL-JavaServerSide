package com.minka.tunel.domain.repository;

import com.minka.tunel.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
