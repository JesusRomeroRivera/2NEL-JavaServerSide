package com.minka.tunel.domain.repository;

import com.minka.tunel.domain.model.CreditCard;
import com.minka.tunel.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
    @Query(value = "SELECT * FROM credit_cards cc WHERE cc.user = :user",nativeQuery = true)
    Optional<CreditCard> getCreditCardByUserID(@Param("user") User user);
}
