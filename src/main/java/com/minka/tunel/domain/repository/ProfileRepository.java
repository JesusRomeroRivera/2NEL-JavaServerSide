package com.minka.tunel.domain.repository;

import com.minka.tunel.domain.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    @Query(value = "SELECT * FROM profiles p WHERE p.eMembershipType = :eMembershipType", nativeQuery = true)
    Optional<Profile> getProfileByMembershipType(@Param("eMembershipType") String eMembershipType);
}
