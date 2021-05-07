package com.minka.tunel.domain.repository;

import com.minka.tunel.domain.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
